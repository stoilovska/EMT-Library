import axios from '../custom-axios/axios';

const EBookShopService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`); // /books/delete/5
    },
    markedAsTakenBook: (id) => {
        return axios.post(`/books/markedAsTaken/${id}`);
    },
    addBook: (name, author, category, availableCopies) => {
        return axios.post("/books/add", {
            "name": name,
            "author": author,
            "category": category,
            "availableCopies": availableCopies
        });
    },
    editBook: (id, name, category, author, availableCopies) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "author": author,
            "availableCopies": availableCopies
        });
    },

    getBook: (id) => {
        return axios.get(`/books/${id}`);
    }
    //,
    // getAllBooksPagination: () => {
    //     return axios.get("/books/pagination", {
    //         params: {
    //             size: 3,
    //             page: 1
    //2         }
    //     });
    // }

}
export default EBookShopService;