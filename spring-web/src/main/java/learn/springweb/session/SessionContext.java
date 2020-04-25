package learn.springweb.session;

/**
 * Session工具类，存储每个线程的session信息
 *
 * @see SessionFilter
 */
public class SessionContext {
    private static ThreadLocal<SessionInfo> sessionContext = new ThreadLocal<>();

    public static void setSessionInfo(SessionInfo sessionInfo) {
        sessionContext.set(sessionInfo);
    }

    public static SessionInfo getSessionInfo() {
        SessionInfo sessionInfo = sessionContext.get();
        if (sessionInfo == null) {
            sessionInfo = new SessionInfo();
            sessionContext.set(sessionInfo);
        }
        return sessionContext.get();
    }

    /**
     * 清除当前线程的ThreadLocal，防止线程复用导致内存泄漏
     */
    public static void clear(){
        sessionContext.remove();
    }
}
