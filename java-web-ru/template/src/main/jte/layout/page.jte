@import gg.jte.Content
@param Content content

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Hexlet Javalin Example</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header>
 <nav>
     <ul>
         <li><a href="/users">Users</a></li>
         <li><a href="/">Main page</a></li>
     </ul>
 </nav>
</header>
${content}

<footer>
<p><a href="https://github.com/gorelikova1993" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">Yulia Baranova </a></p>
</footer>
</body>
</html>