<div style="text-align: center;">

#   **DAvito**
##  **WEB Service**

</div>

----
### Описание приложения
Сервис для покупки и продажи товаров

Используемые технологии:

* Java
* Maven
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Docker
* Nginx
----
### Функции приложения

- Регистрация нового пользователя
- Регистрация нового товара
- Покупка и продажа товаров
----
### Установка и настройка
#### 1. Загрузите необходимые пакеты:
```
sudo apt-get update
sudo apt upgrade
sudo apt-get install docker-compose -y
sudo apt-get install nginx -y
sudo apt-get install systemd -y
sudo apt-get install openjdk-17-jdk -y
sudo apt-get install maven -y
```
#### 2. Создайте следующие директории и выдайте необходимые права:
```
sudo mkdir /home/superuser/app
sudo mkdir -p /var/www/app/davito
sudo chmod 755 -R /var/www/app/davito
```
#### 3. Перейдите в нужную директорию и клонируйте репозиторий:
```
cd /home/superuser/app
sudo git clone https://github.com/Tomas-vinni/adm-exam.git
cd adm-exam/
```
User: Tomas-vinni
Pass: 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

#### 4. Осуществите сборку проекта:
```
sudo mvn install
```
#### 5. Разверните базу данных в Docker контейнере:
```
sudo docker-compose up --build -d
```
#### 6. Скопируйте jar файл для релиза:
```
sudo cp /home/superuser/app/davito/adm-exam/target/davito-0.0.1.jar /var/www/app/davito
```
#### 7. Создайте демона, запускающего приложение:
```
sudo nano /etc/systemd/system/davito.service

[Unit]
Description=Java App
After=syslog.target
After=network.target[Service]
User=superuser
Type=simple

[Service]
ExecStart=/usr/bin/java -jar /var/www/app/davito/davito-0.0.1.jar
Restart=always
StandardOutput=syslog
StandardError=syslog
SyslogIdentifier=davito.service

[Install]
WantedBy=multi-user.target
```
#### 8. Проверьте статус демона:
```
sudo systemctl daemon-reload
sudo systemctl restart davito.service
sudo systemctl status davito.service
```
#### 9. Измените настройки по умолчанию Nginx для настройки revers proxy:
```
sudo nano /etc/nginx/sites-available/default

server {
	listen 80;
	listen [::]:80;
	server_name _;
	
	location / {
		proxy_pass         http://127.0.0.1:5000/;
	}
}
```
### Внимание!!! Оставить только указанные выше строки, остальное закомментировать символом #!!!
#### 10. Перезапустите Nginx и проверьте его статус:
```
sudo nginx -t
sudo systemctl restart nginx
sudo systemctl status nginx
```

После этого установить в настройках виртуального образа Ubuntu Server в Virtual Box -> Сеть -> Тип подключения -> Сетевой мост -> Ok

#### Для того чтобы узнать ip адрес сервера - введите команду на сервере:
```
ip a
```
После запуска приложение будет доступно по адресу: http://<ip адрес сервера>:80/.
### Внимание!!! http без s!!!

#### 11. История команд и тестовый запрос.
```
history | less
```
Запрос теста по адресу: http://<ip адрес сервера>:80/test.

----

#### Вклад и обратная связь
Если вы хотите внести свой вклад в развитие DAvito или обнаружили проблему, пожалуйста, создайте issue в репозитории проекта или отправьте pull request с вашими предложениями.

