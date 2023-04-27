import React from 'react';
import {Link} from 'react-router-dom';

const bookTerm = (props) => {
    return (
        <tr key = {props.term.id}>
            <td>{props.term.name}</td>
            <td>{props.term.author.name}</td>
            <td>{props.term.category.toString()}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <button title={"Delete"} className={"btn btn-danger mx-1"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </button>
                <Link className={"btn btn-info mx-1"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <button title={"markedAsTaken"} className={"btn btn-dark mx-1"}
                        onClick={() => props.onMarkedAsTakenBook(props.term.id)}>
                    Marked As Taken
                </button>
            </td>
        </tr>

    )
}

export default bookTerm;