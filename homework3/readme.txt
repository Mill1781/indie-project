作者：邱翊碩
修課班別：航空太空工程學系學士班
系級：航太系117
學號：F44134057

///////////////////////////////////////////////////////////////////////////////////////////////////(我是分隔線)

主程式檔案名稱：hw3.java

其他檔案：
	Data.java
	Student.java
	Updator.java
	Text.java
	Score.txt

1.使用說明
	
	程式用途簡介：
	本系統為一個簡易的學生成績管理系統，可進行學生資料的新增、刪除、查詢與平均計算，
	並自動將變更同步更新至 Score.txt。


	使用前確保:
	所有檔案 (HW3.java, Data.java, Student.java, Updator.java, Text.java) 都放在同一個資料夾底下，
	並且該資料夾中存在一個名為 Score.txt 的檔案(用來儲存成績資料)。


	**使用順序**, 在終端機或 cmd 中依序輸入以下指令進行編譯與執行：

	a. javac HW3.java(編譯)

	b. java HW3 Score.txt(執行)
	
	c. 程式開始執行(列出程式指令清單，在readme.txt下方也附著指令的使用方法與效果)

	d. 輸入指令

	e. 執行指令

	註1. 若出現 Score.txt didn't be found 的訊息，請先建立該檔案，或確認檔案路徑與名稱是否正確。
	若程式成功啟動，將會印出指令說明與歡迎畫面。

	註2. 如欲在e退出，請直接輸入exit，否則程式會在d和e持續輪迴進行。


2.使用上的須知
	a.所有操作皆以指令的形式輸入，請依照畫面提示的格式輸入
	b.程式會根據輸入內容進行分析，若格式錯誤、空白或逗號位置不正確，系統會提示錯誤
	c.所有數字分數需為 0~100 的整數，若輸入字母或超出範圍，系統會跳出錯誤訊息
	d.程式啟動時會自動讀取 Score.txt 的資料，並於每次修改後自動更新該檔案，還請使用者使用該程式修改Score.txt，手動修改是可以做到的，但很不推薦
	e.指令的空格與大小寫、逗號會影響指令的判讀，還請多加注意
	f.若 Score.txt 為空或尚未建立資料，某些指令會顯示「student list is empty」
	g.若要清空所有學生資料，可使用 clear 指令重置資料表

3.以下為程式指令

	a. add [name] [score1,score2,score3,final1,final2]
	新增一位學生與其成績資料(共五項：三個作業 + 兩個期末專案)
	ex. add Alice 100,0,45,98,48

	b. delete [name]
	刪除指定學生的資料
	ex. delete Alice

	c. show individual score [name]
	顯示指定學生的所有作業與期末專案成績
	ex. show individual score Alice

	d. show individual average [name]
	顯示指定學生的平均分數
	ex. show individual average Alice

	e. show Homework[number] average
	顯示所有學生在該作業的平均分數
	ex. show Homework1 average

	f. show Final Project[number] average
	顯示所有學生在該期末專案的平均分數
	ex. show Final Project1 average

	g. show total
	列出所有學生的完整成績清單
	ex. show total

	h. clear
	清空所有學生的資料
	ex. clear

	i. help
	顯示所有指令的使用說明
	ex. help

	j. exit
	離開程式
	ex. exit
	
	k. sorting
	依照每位學生的平均值從上到下由高到低做排序，並印出結果(注意Score.txt的資料排序會跟著改變)
	ex. sorting

4.其他補充說明
	a. 程式在執行期間會不斷等待指令輸入，直到使用者輸入 exit 才會結束
	b. 若格式正確，會顯示「command success」；若格式錯誤，則會顯示相對應錯誤說明
	c. 程式設有例外處理，避免因輸入錯誤導致崩潰
	d. 若真的需測試，可比照以下格式在Score.txt進行資料修改(非常不推薦)：
	  Alice 80 90 70 85 95
	  Bob 70 75 60 80 90
	f. 程式本身是用英文表示，如果對於某項功能有疑問可以再參考本文件
