
import { bindActionCreators } from "redux";
import { GET_ERRORS, USER_PENDING_ERROR, ADD_BOOKS_ERROR, UPDATE_ERROR_STATUS, GET_USER_DELETE_ERRORS } from "../actions/types";


const initialState = {};

export default function (state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      // TESTING FLOW
      return action.payload;

    case USER_PENDING_ERROR:
      // TESTING FLOW
      return {
        ...state,
        pending: "This is account is not yet approved!",
      }

    // TO DO - figure out what needs to come here.
    case ADD_BOOKS_ERROR:
      return {
        ...state,
        bookErrors: action.payload
      }
    case UPDATE_ERROR_STATUS:
      return {
        ...state,
        bookErrors: action.payload
      }

    case USER_PENDING_ERROR:
      return {
        ...state,
        pending: action.payload
      }

    case GET_USER_DELETE_ERRORS:
      return {
        message: `${action.payload.username} cannot be deleted`
      }
    default:
      return state;
  }
}