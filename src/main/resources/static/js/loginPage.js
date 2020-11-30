const e = React.createElement;
ReactDOM.render(e(LoginForm), document.getElementById('loginForm'))

class LoginForm extends React.Component {
    render() {
        return (
            <form action={"/authenticate"}>
                <div className="input-group mb-3">
                    <div className="input-group-prepend">
                        <span className="input-group-text" id="inputGroup-sizing-default">Login</span>
                    </div>
                    <input type="text"
                           className="form-control"
                           aria-label="Default"
                           aria-describedby="inputGroup-sizing-default"
                           placeholder={"Login"}
                           name={"username"}/>
                </div>
                <div className="input-group mb-3">
                    <div className={"input-group-prepend"}>
                        <span className={"input-group-text"} id="inputGroup-sizing-default">Password</span>
                    </div>
                    <input type="password"
                           className="form-control"
                           aria-label="Default"
                           aria-describedby="inputGroup-sizing-default"
                           placeholder={"Login"}
                           name={"password"}/>
                </div>
            </form>
        );
    }
}


