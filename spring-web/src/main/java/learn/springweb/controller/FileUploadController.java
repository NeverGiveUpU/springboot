package learn.springweb.controller;

import learn.springcommon.storage.LocalStorage;
import learn.springcommon.storage.StorageFileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.stream.Collectors;

/**
 * @author 陈濛
 * @date 2020/4/25 8:33 下午
 */

@Slf4j
@Controller
public class FileUploadController {

    private final LocalStorage storage;

    @Autowired
    public FileUploadController(LocalStorage storage) {
        this.storage = storage;
    }

    @GetMapping("/files")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute(
                "files",
                storage.loadAll().map(
                        path -> MvcUriComponentsBuilder
                                .fromMethodName(
                                    FileUploadController.class,
                                    "serveFile",
                                    path.getFileName().toString())
                                .build()
                                .toUri()
                                .toString())
                        .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storage.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/files")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            String error = "读取上传的文件错误";
            log.error(error, e);
            throw new RuntimeException(error);
        }
        storage.store(inputStream, file.getSize(), file.getContentType(), file.getOriginalFilename());
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/files";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
