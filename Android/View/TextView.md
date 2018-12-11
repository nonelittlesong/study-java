## 获取TextView的真实高度
```
private static int getRealTextViewHeight(@NonNull TextView textView) {
    int textHeight = textView.getLayout().getLineTop(textView.getLineCount());
    int padding = textView.getCompoundPaddingTop() + textView.getCompoundPaddingBottom();
    return textHeight + padding;
}
```
