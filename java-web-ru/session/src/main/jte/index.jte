@import exercise.dto.MainPage
@param MainPage page

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hello Hexlet!</title>
</head>
<body>
<main>
    <h1>Привет, Хекслет!</h1>
    @if(page.getName() != null)
        Добро пожаловать, ${page.getName().toString()}.
        Чтобы разлогиниться, удалите куку JSESSIONID из браузера
        <a href="sessions/delete">Выйти</a>
    @else
        <a href="/sessions/build">Войти</a>
    @endif
</main>
</body>
</html>