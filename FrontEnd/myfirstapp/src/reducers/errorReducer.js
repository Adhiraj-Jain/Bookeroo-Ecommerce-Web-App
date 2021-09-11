import { bindActionCreators } from "redux";
import { GET_ERRORS, USER_PENDING_ERROR, ADD_BOOKS_ERROR } from "../actions/types";

const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    case GET_ERRORS:
      // TESTING FLOW
      console.log("1 We Reach Here (@errorReducer.js)", action.payload);
      
      return action.payload;
      
    case USER_PENDING_ERROR:
      // TESTING FLOW
      console.log("2 We Reach Here (@errorReducer.js)", action.payload);

      return {
        ...state,
        pending: action.payload
      }

    // TO DO - figure out what needs to come here.
    case ADD_BOOKS_ERROR: 
      console.log("We Reach Here (@errorReducer.js)", action.payload);
      
      return {
        ...state,
        bookErrors: action.payload
      }
    

    default:
      return state;
  }
}
