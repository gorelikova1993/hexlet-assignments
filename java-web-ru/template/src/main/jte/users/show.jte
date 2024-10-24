@import exercise.dto.users.UserPage
@param UserPage page

<head>
    <meta charset="utf-8" />
</head>

@template.layout.page(
    content = @`
<main>
    <h1>${page.getUser().getFirstName()}  ${page.getUser().getLastName()}</h1>
</main>
`
)
