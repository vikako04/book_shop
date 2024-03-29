document.addEventListener('DOMContentLoaded', function() 
{
    const form = document.querySelector('form');
    const searchInput = document.getElementById('search');
    const books = document.querySelectorAll('.book');

    form.addEventListener('submit', function(event) 
    {
        event.preventDefault();
        const searchTerm = searchInput.value.trim().toLowerCase();

        books.forEach(function(book) {
            const title = book.querySelector('h2').textContent.trim().toLowerCase();
            const author = book.querySelector('p').textContent.trim().toLowerCase();

            if (title.includes(searchTerm) || author.includes(searchTerm)) 
            {
                book.style.display = 'block';
            } 
            else 
            {
                book.style.display = 'none';
            }
        });
    });
});

