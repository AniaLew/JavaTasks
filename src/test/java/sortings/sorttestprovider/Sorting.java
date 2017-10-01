package sortings.sorttestprovider;

public class Sorting {

  private String kind;
  private long time;

  public Sorting(String kind, long time) {
    this.kind = kind;
    this.time = time;
  }

  public String getKind() {
    return kind;
  }

  public long getTime() {
    return time;
  }
}
