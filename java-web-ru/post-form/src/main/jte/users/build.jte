@template.layout.page(
    content =@`
    <form action="/users" method="post">
        <div class="mb-3">
            <label class="form-label">
                Имя
                <input type="text" name="firstName" class="form-control"/>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Фамилия
                <input type="text" name="lastName" class="form-control"/>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Email
                <input type="email" required name="email" class="form-control" />
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">
                Пароль
                <input type="password" required name="password" class="form-control"/>
            </label>
        </div>
        <input type="submit" value="Зарегистрировать" class="btn btn-primary"/>
    </form>
`
)