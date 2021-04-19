# Obraski

# What is it?
It's image hosting with capability to upload images and share link with your friends. It allows you to create account and after email activation use all possibilities that project gives you. It uses progressive image loading(medium.com like) for better user experience.

# Technology stack
- Spring boot web as web framework
- PostgreSQL and Spring Data for persistance
- Vue and Axios for front and consuming REST
- Material Design Lite for website apperance
- Spring Security and Spring Mail for account activation and security and then run created jar by java -jar command.

# Endpoints
GET `/` - returns homepage for guest

GET `/user` - returns logged user page

GET `/register` - returns page with registration form

POST `/register` - registers profile

GET `/login` - return login page

POST `/login` - logs user in

GET `/activation/{token}` - activatates account with given token

POST `/upload` - uploads image to server

GET `/images/{name}` - returns full size image with given name

GET `/images/{token}` - returns full size image with given token

GET `/images/thumb/{name}` -returns image thumbnail with given name

GET `/images/thumb/{token}` - returns image thumbnail with given token

GET `/userinfo` - returns JSON with basic information about logged user

POST `/images/delete` - delete image
# Screenshots

Userpage
![](https://github.com/Kubarant/ScreenerSaver/blob/master/src/main/resources/static/screenshots/userpage.png)

Uploading images
![](https://github.com/Kubarant/ScreenerSaver/blob/master/src/main/resources/static/screenshots/uploading.png)

Login page
![](https://github.com/Kubarant/ScreenerSaver/blob/master/src/main/resources/static/screenshots/login.png)

Register page
![](https://github.com/Kubarant/ScreenerSaver/blob/master/src/main/resources/static/screenshots/register.png)


