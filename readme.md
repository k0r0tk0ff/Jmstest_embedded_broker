 
 
  ### Задача 1 
Написать тест, который будет отправлять сообщения в брокер и принимать в разных режимах как сессий (AUTO_ACK, TRANSACTED, etc.),  
так и сообщений (персистентные и неперсистентные).  
Оформить итоговый файл с результатами записи и чтения в каждом режиме.  
Объяснить полученные результаты.  
Предполагаемое время решения 6-10 часов.

  ### Задача 2 
В задаче 1 использовать embedded брокер и VM-коннектор, после чего снова проверить все режимы.  
Сравнить скорость работы по сравнению с использованием tcp коннектора. Объяснить полученные результаты.  
Предполагаемое время решения 4-6 часов.

  #### Решение Задачи 1.  
  
  Используется "tcp://localhost:61616"

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

  #### Решение Задачи 2.

  Используется "vm://broker"  
    
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
  
  
  


