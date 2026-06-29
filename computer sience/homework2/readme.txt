作者：邱翊碩
修課班別：航空太空工程學系學士班
系級：航太系117	
學號：F44134057

/////////////////////////////////

主程式檔案名稱：hw1.java

其他檔案：user.java
	  movie.java

使用說明：
	1.編譯與執行
	確認電腦路徑無誤後在此路徑的cmd上用javac來進行編譯，之後繼續在同個cmd上輸入java hw2即可執行
	ex. /path/ javac hw2.java
	    	   java hw2
	      	   ...(程式開始)



	2.使用上的須知
	程式在執行時會跳出選項並詢問服務的需求，依照指示輸入指定數字或字母即可使用程式，輸入的格式大致上都可以辨別，但如果可以輸入一些其他系統的指令或不在考慮範圍內的文字可能為導致crash，
	輸入完成後都會顯示command received的訊息提示輸入成功。然後如果使用了show movie後無法輸出中文的話，那可能要請使用者確定一下自己電腦的設定是否正確，不然也可以選擇直接在visual studio
	code開啟後，在裡面的cmd編譯並執行。
	還有另一的重要的須知，本程式至多只能容納999筆資訊，如果今天資訊量超過了999，程式將出現錯誤，如果使用者真的使用了999筆以上的訂單，請善用clear() function或者重啟程式來清空資料。




	3.以下為程式的指令

	a.add [電影代號] [張數] [user_id] , 可以輸入使用者以及訂票資訊，若想知道電影編號的資訊請輸入show movie
	ex. add A 12 Eason

	b.show movie , 列印出現在上映的電影，資訊包含了名子、電影代號、時長以及種類
	ex. show movie
		
	...(電影資訊)

	c.show movie [類型] , 該功能會列出相同類型的所有電影以及其相關資訊的清單，如電影名稱、電影時長、電影類型、電影代號。
	ex. show movie horror


	...(電影資訊)

	d.show movie [時長] , 顯示出在該時長內的所有電影相關資訊的清單，同c.
	ex. show movie 120


	...(電影資訊)
	
	e.show order [user_id], 顯示出某位使用者的下單紀錄
	ex. show eason
	

	...(使用者資訊)

	f.cancel order [user_id] [order_id], 刪除某位使用者的某筆訂購紀錄，之後再使用指令e.也將不會列出
	ex. cancel order eason 1

	g.clear, 可以用來清空所有訂單	
	ex. clear
	
	
	...()

	h.help, 如果使用者忘記指令，可以輸入help重新列出指令選項
	ex. help
	

	...(指令)

	h.exit, 用於關閉系統


	
	
