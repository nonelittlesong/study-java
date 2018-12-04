private boolean capture() {
    String mPath = genSaveFileName(getTitle().toString() + "_", ".png");
    File imageFile = new File(mPath);
    if (imageFile.exists()) {
        imageFile.delete();
    }

    // create bitmap screen capture
    Bitmap bitmap = textureView.getBitmap();
    OutputStream outputStream = null;

    try {
        outputStream = new FileOutputStream(imageFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);
        outputStream.flush();
        outputStream.close();

    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return false;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    return true;
}

private String genSaveFileName(String prefix, String suffix) {
    Date date = new Date();
    SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd_hhmmss");
    String timeString = dateformat1.format(date);
    String externalPath = Environment.getExternalStorageDirectory().toString();
    return externalPath + "/" + prefix + timeString + suffix;
}
