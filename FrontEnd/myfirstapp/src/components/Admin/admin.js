import React, {Component} from 'react';
import CreateUserButton from "../Persons/CreateUserButton";
import { connect } from "react-redux";
import PropTypes from "prop-types";
import ReviewUsersButton from "../Persons/ReviewUsersButton";

class Admin extends Component {
    constructor() {
        super();

        this.state = {
            message: ""
        };
    }

    componentWillReceiveProps(nextProps) {
        this.setState({ message: nextProps.errors.message ? nextProps.errors.message : "" });
    }

    render() {
        return (
            <div className="Persons">
                <div className="container">
                    {this.state.message.length > 0 && (<div className="alert alert-success text-center" role="alert">
                        {this.state.message}
                    </div>)}
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Admin Page</h1>
                            <br/>
                            <br/>
                            <div>
                                <table className="table align-center text-center w-50"align="center">
                                    <tbody>
                                    <tr>
                                        <th>Create a user</th>
                                        <th><CreateUserButton /></th>
                                    </tr>
                                    <tr>
                                        <th>Review user accounts</th>
                                        <th><ReviewUsersButton /></th>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
Admin.propType = {

};

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(
    mapStateToProps,
    {}
)(Admin);