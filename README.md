# NovaZHConverter
遊戲王專有名詞繁簡轉換器  
功能說明  
將簡體文字和繁體文字互轉，並且套用對應的遊戲王專有名詞  

採用Lib:  
https://github.com/opentalking/java-zhconverter-read-only

採用專用名詞:  
https://github.com/salix5/CardEditor/blob/master/data.txt

執行畫面  
![image](http://i.imgur.com/HevCAT5.png "image")

需求環境  
JDK 1.8 
  
操作方式  
0.執行目錄底下的NovaZHConverter.jar (點兩下)  
1.將簡體文字貼到左邊，按下簡體轉繁體按鈕，即可轉換成右邊的繁體文字，並且複製到剪貼簿。  
2.將繁體文字貼到右邊，按下繁體轉簡體按鈕，即可轉換成左邊的簡體文字，並且複製到剪貼簿。  

程式運作過程會參考目錄底下的ygo.txt檔案，  
可自行修改此檔案，製作出特定領域專有名詞的參照  
如讀取不到ygo.txt檔案，則只會進行一般的繁簡轉換  
