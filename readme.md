 
 
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
		Session.SESSION_TRANSACTED  - 29 nanosec (session.commite() required!)  
		Session.AUTO_ACKNOWLEDGE    - 25 nanosec   
		Session.DUPS_OK_ACKNOWLEDGE - 23 nanosec   
		Session.CLIENT_ACKNOWLEDGE  - 23 nanosec (message.acknowledge(); required!)    
  
Персистентные   
		Session.SESSION_TRANSACTED  -  56 nanosec (session.commite() and kahaDb required!)   
		Session.AUTO_ACKNOWLEDGE    -  31 nanosec  
		Session.DUPS_OK_ACKNOWLEDGE -  42 nanosec   
		Session.CLIENT_ACKNOWLEDGE  -  37 nanosec (message.acknowledge() and kahaDb required!) 

  #### Решение Задачи 2.

  Используется "vm://broker"  
    
Рассматриваем следующие случаи -   
	Неперсистентные   
		Session.SESSION_TRANSACTED  -  34 nanosec (session.commite() required!)  
		Session.AUTO_ACKNOWLEDGE    -  26 nanosec   
		Session.DUPS_OK_ACKNOWLEDGE -  23 nanosec   
		Session.CLIENT_ACKNOWLEDGE  -  22 nanosec (message.acknowledge(); required!)    
  
Персистентные   
		Session.SESSION_TRANSACTED  -  46 nanosec (session.commite() and kahaDb required!)   
		Session.AUTO_ACKNOWLEDGE    -  36 nanosec  
		Session.DUPS_OK_ACKNOWLEDGE -  28 nanosec   
		Session.CLIENT_ACKNOWLEDGE  -  29 nanosec (message.acknowledge() and kahaDb required!)  
		
  ----------------------------------------------------------------------------------------------------  
  
  ### Выводы 
  1) Неперсистентные сообщения при использовании режима коннектора "vm" и "tcp" отрабатывают примерно одинаково (+- 5 ns)
  2) Персистентные сообщения при использовании режима коннектора "vm" отрабатывают быстрее примерно на 5 ns
  
  
  


