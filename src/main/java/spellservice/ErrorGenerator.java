package spellservice;

import net.yandex.speller.services.spellservice.SpellError;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by Shadilan on 06.09.2016.
 */

@Component
public class ErrorGenerator {
    @PostConstruct
    public void initData() {

    }

    public SpellError[] getError(String text) {
        String[] wordArray=text.replaceAll("[\\,,\\.]"," ").replaceAll("  "," ").split("[ ,\\.,\\,]");
        //Определить количестве строк в тексте.
        int wordCount=wordArray.length;
        //Определить количество ошибочных слов.
        int errCnt=(int)(wordCount*(utils.random(6))/100);
        //Выбрать ошибочные слова
        if (errCnt>0) {
            ArrayList<SpellError> errorWords = new ArrayList();
            for (int i = 0; i < errCnt; i++) {
                SpellError spellError=new SpellError();
                int wordNum=utils.random(wordCount);
                String errWord=wordArray[wordNum];
                int index=0;
                int oldIndex=0;
                int oldRow=0;
                int oldCol=0;
                index=text.indexOf(errWord,index);
                while (index!=-1) {

                    spellError.setWord(errWord);
                    spellError.setLen(errWord.length());
                    String[] s = new String[1];
                    s[0] = "Тестовая подсказка";
                    spellError.setS(s);
                    spellError.setCode(1);
                    spellError.setPos(index);
                    int row=oldRow;
                    int col=oldCol;
                    for (int j=oldIndex;j<index;j++){
                        if (text.charAt(j)=='\n'){
                            row++;
                            col=-1;
                        } else col++;
                    }
                    //Определить позицию
                    text.indexOf(errWord);
                    spellError.setCol(col);
                    spellError.setRow(row);
                    oldIndex=index;
                    oldCol=col;
                    oldRow=row;
                    index=text.indexOf(errWord,index);
                    errorWords.add(spellError);
                }
            }
            return (SpellError[])errorWords.toArray();
        } else
        return new SpellError[0];
    }
    public SpellError[][] getErrors(String[] texts) {
        int cnt=texts.length;
        SpellError[][] result=new SpellError[cnt][];
        for (int i=0;i<cnt;i++){
            result[i]=getError(texts[i]);
        }
        return result;
    }
}
