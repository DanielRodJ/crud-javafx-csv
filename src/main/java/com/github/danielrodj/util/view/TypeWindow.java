package com.github.danielrodj.util.view;

public enum TypeWindow {
        LOGIN(
                        "loginWindow",
                        "Login",
                        new Size(360, 360)),
        DASHBOARD(
                        "dashboard",
                        "Dashboard",
                        new Size(800, 600, 1000, 800, Double.MAX_VALUE, Double.MAX_VALUE)),
        RECORD_VIEWER(
                        "recordsViewer",
                        "Records Viewer",
                        new Size(570, 525, 1000, 640, Double.MAX_VALUE, Double.MAX_VALUE)),
        LABEL_FORM(
                        "labelForm",
                        "Label Form",
                        new Size(400, 400)),
        LABEL_INFO(
                        "labelInfo",
                        "Label Data",
                        new Size(610, 410));

        private static final String BASE_PATH = "/com/github/danielrodj/fxml/";
        private final String filePath;
        private final String title;
        private final Size size;

        TypeWindow(String fileName, String title, Size size) {
                this.filePath = BASE_PATH + fileName + ".fxml";
                this.title = title;
                this.size = size;
        }

        public String getfilePath() {
                return filePath;
        }

        public String getTitle() {
                return title;
        }

        public Size getSize() {
                return size;
        }
}
