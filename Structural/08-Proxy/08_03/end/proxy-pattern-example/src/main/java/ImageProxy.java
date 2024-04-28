public class ImageProxy implements DisplayObject {

  String path;
  ImageFile imageFile;

  public ImageProxy(String path) {
    this.path = path;
  }

  public void display() {
    if(imageFile == null) {
      imageFile = new ImageFile(path);
    }
    imageFile.display();
  }
}
