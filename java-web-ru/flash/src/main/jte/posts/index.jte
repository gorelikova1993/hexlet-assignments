@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`

    <h1>Все посты</h1>
    <table class="table table-striped">
        @for(var post : page.getPosts())
            <tr>
                <td>${post.getId()}</td>
                <td><a href="/posts/${post.getId()}">${post.getName()}</a></td>
            </tr>
        @endfor
    </table>
    @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType()}" role="alert">
            ${page.getFlash()}
        </div>
    @endif
`)