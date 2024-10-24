
@import exercise.dto.users.UsersPage
@param UsersPage page
@template.layout.page(
content = @`
    <form action="/users" method="get">
        <input type="search" name="name" value="${page.getFirstName()}" />
        <input type="submit" value="Search" />
    </form>

    <h1>Пользователи</h1>
    <table class="table table-striped">
        @for(var user : page.getUsers())
            <tr>
                <td>
                    ${user.getId()}
                </td>
                <td>
                    <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                </td>
            </tr>
        @endfor
    </table>
`
)
