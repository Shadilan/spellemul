package spellservice;

/**
 * Created by zhitnikov.bronislav on 05.09.2016.
 */
public class ErrorItem {
    private int code;
    private int pos;
    private int row;
    private int col;
    private int len;
    public ErrorItem(int code,int pos,int row,int col,int len){
        this.code=code;
        this.pos=pos;
        this.row=row;
        this.col=col;
        this.len=len;

    }
}
