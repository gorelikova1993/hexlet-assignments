@import exercise.dto.users.UsersPage
@param UsersPage page


<head>
    <meta charset="utf-8" />
</head>
<body>
@template.layout.page(
content = @`
    @for(var user : page.getUsers())
        <div>
            <h1>${user.getFirstName()}  ${user.getLastName()}</h1>
        </div>
    @endfor
`
)
</body>
