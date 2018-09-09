 
 
 Задача 1 
Написать тест, который будет отправлять сообщения в брокер и принимать в разных режимах как сессий (AUTO_ACK, TRANSACTED, etc.),  
так и сообщений (персистентные и неперсистентные).  
Оформить итоговый файл с результатами записи и чтения в каждом режиме.  
Объяснить полученные результаты.  
Предполагаемое время решения 6-10 часов.

 Задача 2 
В задаче 1 использовать embedded брокер и VM-коннектор, после чего снова проверить все режимы.  
Сравнить скорость работы по сравнению с использованием tcp коннектора. Объяснить полученные результаты.  
Предполагаемое время решения 4-6 часов.

Решение.

Рассматриваем следующие случаи -   
	Неперсистентные   
		Session.SESSION_TRANSACTED  - 175 nanosec (session.commite() required!)  
		Session.AUTO_ACKNOWLEDGE    - 70 nanosec   
		Session.DUPS_OK_ACKNOWLEDGE - 73 nanosec   
		Session.CLIENT_ACKNOWLEDGE  - 101 nanosec (message.acknowledge(); required!)    
  
Персистентные   
		Session.SESSION_TRANSACTED  -  nanosec (session.commite() and kahaDb required!)   
		Session.AUTO_ACKNOWLEDGE    -  nanosec  
		Session.DUPS_OK_ACKNOWLEDGE -  nanosec   
		Session.CLIENT_ACKNOWLEDGE  -  nanosec (message.acknowledge() and kahaDb required!)    
  
    Выставляем уровень логирования "error" чтобы отсеять ненужную информацию
    
-----------------------------------------------------------------------------------  
2018-09-09 16:42:17:959 ERROR Main:27 - MODE = Session.SESSION_TRANSACTED  
2018-09-09 16:42:17:962 ERROR Main:28 - IS_TRANSACTED = true  
2018-09-09 16:42:17:962 ERROR Main:29 - IS_PERSISTANT = false  
2018-09-09 16:42:18:842 ERROR Sender:50 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-09 16:42:19:019 ERROR Receiver:49 - Success received message. Message contains that - 'Message for send'    

-----------------------------------------------------------------------------------    
2018-09-09 16:32:32:425 ERROR Main:27 - MODE = Session.AUTO_ACKNOWLEDGE  
2018-09-09 16:32:32:427 ERROR Main:28 - IS_TRANSACTED = false  
2018-09-09 16:32:32:428 ERROR Main:29 - IS_PERSISTANT = false  
2018-09-09 16:32:33:281 ERROR Sender:50 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-09 16:32:33:349 ERROR Receiver:49 - Success received message. Message contains that - 'Message for send'  
  
  ----------------------------------------------------------------------------------    
2018-09-09 16:33:54:209 ERROR Main:27 - MODE = Session.DUPS_OK_ACKNOWLEDGE  
2018-09-09 16:33:54:212 ERROR Main:28 - IS_TRANSACTED = false  
2018-09-09 16:33:54:212 ERROR Main:29 - IS_PERSISTANT = false  
2018-09-09 16:33:55:094 ERROR Sender:50 - Message has been sent success. Message contains that - 'Message for send'     
2018-09-09 16:33:55:169 ERROR Receiver:49 - Success received message. Message contains that - 'Message for send'    

  ----------------------------------------------------------------------------------  
  
2018-09-09 16:39:47:994 ERROR Main:27 - MODE = Session.CLIENT_ACKNOWLEDGE  
2018-09-09 16:39:47:997 ERROR Main:28 - IS_TRANSACTED = false  
2018-09-09 16:39:47:997 ERROR Main:29 - IS_PERSISTANT = false  
2018-09-09 16:39:48:861 ERROR Sender:50 - Message has been sent success. Message contains that - 'Message for send'  
2018-09-09 16:39:48:960 ERROR Receiver:49 - Success received message. Message contains that - 'Message for send'    

  ----------------------------------------------------------------------------------    
  


