import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import jwt_decode from "jwt-decode";

class Home extends Component {
    constructor() {
        super();

        this.state = {
            isUserLoggedIn: false
        };
    }

    componentDidMount() {
        const token = localStorage.getItem("jwtToken");
        if (token) {
            const decoded_token = jwt_decode(token)

            if (decoded_token.username) {
                this.setState({isUserLoggedIn: true})
            } else {
                this.setState({isUserLoggedIn: false})
            }
        } else {
            this.setState({isUserLoggedIn: false})
        }
        
        
    }

    componentWillReceiveProps() {
        console.log("Here")
    }


    render() {
        return (
            <div>
                { this.state.isUserLoggedIn && (<Link to='/addbook'>
                    <button className="btn btn-light my-2 my-sm-0 addBookButton">Add Book</button>
                </Link> )}
            </div>
            
    
        )
    }
}
export default Home;
