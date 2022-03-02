    function loadBooks() {
        $.get('/api/v1/books').done(function (books) {
            $('#books > tbody').empty();

            books.forEach(function (book) {
                $('#books > tbody').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.title}</td>
                        <td>${book.author.name}</td>
                        <td>${book.genre.name}</td>
                        <td>
                            <a href="#" onclick="editBook(${book.id})">Edit</a>
                            <a href="#" onclick="deleteBook(${book.id})">Delete</a>
                        </td>
                    </tr>
                `)
            });
        })
    }

    function loadNotes(bookId) {
        if (!bookId) {
            return;
        }

        $.get('/api/v1/book/' + bookId + '/notes').done(function (notes) {
            $('#notes > tbody').empty();

            notes.forEach(function (note) {
                $('#notes > tbody').append(`
                    <tr>
                        <td>${note.id}</td>
                        <td>${note.note}</td>                        
                        <td>
                            <a href="#" onclick="deleteNote(${note.id})">Delete</a>
                        </td>
                    </tr>
                `)
            });
        })
    }

    function loadAuthors() {
        $.get('/api/v1/authors').done(function (authors) {
            $('#bookAuthorId').empty();

            authors.forEach(function (author) {
                $('#bookAuthorId').append(`
                        <option value="${author.id}">${author.name}</option>
                `)
            });
        })
    }

    function loadGenres() {
        $.get('/api/v1/genres').done(function (genres) {
            $('#bookGenreId').empty();

            genres.forEach(function (genre) {
                $('#bookGenreId').append(`
                        <option value="${genre.id}">${genre.name}</option>
                `)
            });
        })
    }

    function clearEditBlock() {
        $('#bookId').val('');
        $('#bookTitle').val('');
        $('#bookAuthorId').val('');
        $('#bookGenreId').val('');
        $('#notes > tbody').empty();
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
        loadNotes(bookId);
        (bookId) ? $('#commentList').show() : $('#commentList').hide();
        $('#bookList').hide();
        $('#bookEditor').show();
    }

    function editBook(id) {
        showEditor(id);

        $.get('/api/v1/books/' + id).done(function (book) {
            $('#bookId').val(id);
            $('#bookTitle').val(book.title);
            $('#bookAuthorId').val(book.author.id);
            $('#bookGenreId').val(book.genre.id);
        })
    }

    function saveBook() {
        let id = $('#bookId').val();
        let method = (id === '') ? 'POST' : 'PUT';

        $.ajax({
            url: '/api/v1/books',
            type: method,
            data: JSON.stringify({
                id: id,
                title: $('#bookTitle').val(),
                authorId: $('#bookAuthorId').val(),
                genreId: $('#bookGenreId').val()
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
            url: '/api/v1/books/' + id,
            type: 'DELETE',
            success: function () {
                loadBooks();
            }
        });
    }

    function addNotes() {
        let bookId = $('#bookId').val();

        $.ajax({
            url: '/api/v1/notes',
            type: 'POST',
            data: JSON.stringify({
                note: $('#noteText').val(),
                bookId: bookId
            }),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function () {
                $('#noteText').val('');
                loadNotes(bookId);
            }
        });
    }

    function deleteNote(noteId) {
        if (!confirm('Are you sure you want to delete this comment?')) {
            return;
        }

        $.ajax({
            url: '/api/v1/notes/' + noteId,
            type: 'DELETE',
            success: function () {
                loadNotes($('#bookId').val());
            }
        });
    }