    function loadBooks() {
        $.get('/books').done(function (books) {
            $('#books > tbody').empty();

            books.forEach(function (book) {
                $('#books > tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td>${book.author.fullName}</td>
                        <td>${book.genre.name}</td>
                        <td>
                            <a href="#" onclick="editBook('${book.id}')">Edit</a>
                            <a href="#" onclick="deleteBook('${book.id}')">Delete</a>
                        </td>
                    </tr>
                `)
            });
        })
    }

    function loadComments(bookId) {
        if (!bookId) {
            return;
        }

        $.get('/books/' + bookId + '/comments').done(function (comments) {
            $('#comments > tbody').empty();

            comments.forEach(function (comment) {
                $('#comments > tbody').append(`
                    <tr>
                        <td>${comment.id}</td>
                        <td>${comment.text}</td>
                        <td>
                            <a href="#" onclick="deleteComment('${comment.id}')">Delete</a>
                        </td>
                    </tr>
                `)
            });
        })
    }

    function loadAuthors() {
        $.get('/authors').done(function (authors) {
            $('#bookAuthorId').empty();

            authors.forEach(function (author) {
                $('#bookAuthorId').append(` <option value="${author.id}" data-author='${JSON.stringify(author)}' >${author.fullName}</option> `)
            });
        })
    }

    function loadGenres() {
        $.get('/genres').done(function (genres) {
            $('#bookGenreId').empty();

            genres.forEach(function (genre) {
                $('#bookGenreId').append(` <option value="${genre.id}" data-genre='${JSON.stringify(genre)}'>${genre.name}</option> `)
            });
        })
    }

    function clearEditBlock() {
        $('#bookId').val('');
        $('#bookName').val('');
        $('#bookAuthorId').val('');
        $('#bookGenreId').val('');
        $('#bookJSON').data('book', '');
        $('#comments > tbody').empty();
    }

    function showList() {
        $('#bookEditor').hide();
        clearEditBlock();

        loadBooks();
        $('#bookList').show();
    }

    function showEditor(bookId) {
        loadAuthors();
        loadGenres();
        loadComments(bookId);

        (bookId) ? $('#commentList').show() : $('#commentList').hide();
        $('#bookList').hide();
        $('#bookEditor').show();
    }

    function editBook(id) {
        showEditor(id);

        $.get('/books/' + id).done(function (book) {
            $('#bookId').val(id);
            $('#bookName').val(book.name);
            $('#bookAuthorId').val(book.author.id);
            $('#bookGenreId').val(book.genre.id);
            $('#bookJSON').data( "book", book);

            $('#bookAuthorId option:not(:selected)').attr('disabled', true);
            $('#bookGenreId option:not(:selected)').attr('disabled', true);
        })
    }

    function saveBook() {
        let id = $('#bookId').val();
        let method = (id === '') ? 'POST' : 'PUT';

        $.ajax({
            url: '/books',
            type: method,
            data: JSON.stringify({
                id: id,
                name: $('#bookName').val(),
                author: $('#bookAuthorId').find(":selected").data("author"),
                genre: $('#bookGenreId').find(":selected").data("genre")
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function () {
                showList();
            }
        });
    }

    function deleteBook(id) {
        if (!confirm('Are you sure you want to delete this book?')) {
            return;
        }

        $.ajax({
            url: '/books/' + id,
            type: 'DELETE',
            success: function () {
                loadBooks();
            }
        });
    }

    function addComment() {
        let data = JSON.stringify({
            text: $('#commentText').val(),
            book: $('#bookJSON').data("book")
        });

        $.ajax({
            url: '/comments',
            type: 'POST',
            data: data,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function () {
                $('#commentText').val('');

                let bookId = $('#bookId').val();
                loadComments(bookId);
            }
        });
    }

    function deleteComment(commentId) {
        if (!confirm('Are you sure you want to delete this comment?')) {
            return;
        }

        $.ajax({
            url: '/comments/' + commentId,
            type: 'DELETE',
            success: function () {
                loadComments($('#bookId').val());
            }
        });
    }