package id.ac.ui.cs.mobileprogramming.izzanfi.phoneinformer.datas;

public class MainMenuItem {
    private int imgCode;
    private String menuTitle;
    private String menuDescription;

    public MainMenuItem(int imgCode, String menuTitle, String menuDescription) {
        this.imgCode = imgCode;
        this.menuTitle = menuTitle;
        this.menuDescription = menuDescription;
    }

    public int getImgCode() {
        return this.imgCode;
    }

    public String getMenuTitle() {
        return this.menuTitle;
    }

    public String getMenuDescription() {
        return this.menuDescription;
    }
}
