import axios from "axios";
import { ADD_BOOKS_ERROR, GET_ERRORS, GET_PERSONS, UPDATE_ERROR_STATUS } from "./types";

export const createBook = (book, history) => async dispatch => {
    try {
        const res = await axios.post("http://localhost:8082/api/books/registerBook", book);
        history.push("/");
        history.push("/addbook");
        dispatch({
            type: UPDATE_ERROR_STATUS,
            payload: { message: book.bookName + " has been successfully registerd." }
        });

    } catch (err) {
        dispatch({
            type: ADD_BOOKS_ERROR,
            payload: err.response.data
        });
    }
}

export const getBooks = () => async dispatch => {
    try {
        const res = await axios.get("http://localhost:8082/api/books/allbooks");
        dispatch({
            type: UPDATE_ERROR_STATUS,
            payload: res.data
        });
    }
    catch (err) {
        dispatch({
            type: UPDATE_ERROR_STATUS,
            payload: err.response.data
        });
    }
};

export const getBook = (id, history) => async dispatch => {
    try {
        const res = await axios.get(`http://localhost:8082/api/books/${id}`);
        dispatch({
            type: UPDATE_ERROR_STATUS,
            payload: res.data
        });
    }
    catch (err) {
        history.push("/book");
    }
};

export const editBook = (book, history) => async dispatch => {
    try {
        const res = await axios.post(`http://localhost:8080/api/admin/editbook/${book.id.isbn}`, book);
        history.push(`/book/${book.id.username}/${book.id.isbn}`);
        dispatch({
            type: UPDATE_ERROR_STATUS,
            payload: res.data
        });
    }
    catch (err) {
        history.push(`/book/${book.isbn}`);
    }
};
