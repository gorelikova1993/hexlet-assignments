@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <main>
        <h1>${page.getPost().getName()}</h1>
        <p>${page.getPost().getBody()}</p>
    </main>
    `)
