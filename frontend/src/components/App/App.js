import './App.css'
import React, {Component} from "react";
import Header from '../Header/header'
import {BrowserRouter as Router, Route} from "react-router-dom";
import Books from '../Books/BookList/books';
import Authors from '../Authors/authors'
import Categories from '../Categories/categories'
import BookAdd from '../Books/BookAdd/bookAdd'
import BookEdit from '../Books/BookEdit/editBook'
import EBookShopService from "../../repository/ebookshopRepository";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            authors: [],
            categories: [],
            selectedBook: {}
        }
    }

    render() {
        return (
            <Router>
                <main>
                    <div className={"container"}>
                        <Header/>
                        <Route path={"/books"} exact
                               render={() => <Books books={this.state.books}
                                                    onDelete={this.deleteBook}
                                                    onEdit={this.getBook}
                                                    onMarkedAsTakenBook={this.markedAsTakenBook}/>}/>

                        <Route path={"/books/add"} exact render={() => <BookAdd authors={this.state.authors}
                                                                                categories={this.state.categories}
                                                                                onAddBook={this.addBook}/>}/>
                        <Route path={"/books/edit/:id"} exact
                               render={() => <BookEdit categories={this.state.categories}
                                                       authors={this.state.authors}
                                                       onEditBook={this.editBook}
                                                       book={this.state.selectedBook}/>}/>

                        <Route path={"/authors"} exact render={() => <Authors authors={this.state.authors}/>}/>
                        <Route path={"/categories"} exact
                               render={() => <Categories categories={this.state.categories}/>}/>

                        <Route path={"/"} exact render={() => <Books books={this.state.books}
                                                                     onDelete={this.deleteBook}
                                                                     onMarkedAsTakenBook={this.markedAsTakenBook}
                                                                     onEdit={this.getBook}/>}/>
                        {/*<Redirect to={"/books"}/>*/}
                    </div>
                </main>
            </Router>
        );
    }


    // componentDidMount() {
    //   this.loadAuthors();
    //   this.loadBooks();
    //   this.loadCategories();
    // }

    componentDidMount() {
        this.fetchData()
    }

    fetchData = () => {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }

    loadAuthors = () => {
        EBookShopService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }


    loadBooks = () => {
        EBookShopService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            })
    }

    loadCategories = () => {
        EBookShopService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            })
    }

    deleteBook = (id) => {
        EBookShopService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    markedAsTakenBook = (id) => {
        EBookShopService.markedAsTakenBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, author, category, availableCopies) => {
        EBookShopService.addBook(name, author, category, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
    getBook = (id) => {
        EBookShopService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                });
            })
    }
    editBook = (id, name, category, author, availableCopies) => {
        EBookShopService.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBooks();
            })
    }
}

export default App;
