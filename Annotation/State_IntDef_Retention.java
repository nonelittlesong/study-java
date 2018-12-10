public class StatusDialog extends Dialog {
    /**
     * @IntDef包住常量
     * @Retention定义策略
     * 自定义注解
    @IntDef ({
            MORE,
            LESS,
            INTERMEDIATE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }
    
    public static final int MORE = 0;
    public static final int LESS = 1;
    public static final int INTERMEDIATE = 2;
    
    // 限定变量
    @State
    private int state;
    
    // 在声明函数的同时限定函数的取值，将其写在需要限定的参数前
    public void setState(@State int state, boolean animate) {
        this.state = state;
        if (state == MORE) {
            fraction = 0f;
        } else if (state == LESS) {
            fraction = 1f;
        } else {
            throw new IllegalArgumentException("Unknown state, must be one of STATE_MORE = 0, STATE_LESS = 1");
        }
        updateArrow(animate);
    }
    
    // 限定返回值
    @State
    private int getFinalStateByFraction() {
        if (fraction < .5f) {
            return MORE;
        } else {
            return LESS;
        }
    }
}
/**
 * Retention
 * RetentionPolicy.SOURCE:  在原文件中有效，被编译器丢弃
 * RetentionPolicy.CLASS:   在class文件有效，可能被虚拟机忽略
 * RetentionPolicy.RUNTIME: 在运行时有效
 * 自定义注解默认是CLASS
 */

        @IntDef({
            VerticalPosition.CENTER,
            VerticalPosition.ABOVE,
            VerticalPosition.BELOW,
            VerticalPosition.ALIGN_TOP,
            VerticalPosition.ALIGN_BOTTOM,
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface VerticalPosition {
        int CENTER = 0;
        int ABOVE = 1;
        int BELOW = 2;
        int ALIGN_TOP = 3;
        int ALIGN_BOTTOM = 4;
    }
    
