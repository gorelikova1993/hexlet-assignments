@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page


@if(page.getErrors() != null)
    <ul>
        @for(var validator : page.getErrors().values())
            @for(var error : validator)
                <li>${error.getMessage()}</li>
            @endfor
        @endfor
    </ul>
@endif

@template.layout.page(
content = @`
    <form action="/articles" method="post">
        <div class="mb-3">
            <label class="form-label">Название статьи</label>
            <input type="text" name="title" class="form-control" value="${page.getTitle()}">
        </div>
        <div class="form-floating">

            <textarea class="form-control" style="height: 100px" name="content">${page.getContent()}</textarea>
            <label class="form-label">Содержание статьи</label>
        </div>
        <input type="submit" value="Сохранить" class="btn btn-primary"/>
    </form>
    `)