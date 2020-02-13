/**
 * Author : 13152
 * Date : 2020/2/9
 * Time : 10:29
 * Version ： 1.0.0
 **/
public interface UF {
    int getSize();
    boolean isConnect(int p, int q);
    void unionElements(int p, int q);
}
