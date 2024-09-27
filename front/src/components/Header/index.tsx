/** @jsxImportSource @emotion/react */
import { useCallback, useContext } from "react";

import {inputapp, inputbox,toogle, tooglebox} from "./styles";
import { TaskContext } from "../../TaskContext";
import { Task } from "../../types";
import { API_URL } from "../../config";

function nanoid(size = 5) {
    let urlAlphabet = "useandom-26T198340PX75pxJACKVERYMINDBUSHWOLF_GQZbfghjklqvwyzrict";
    let id = "";
    let i = size;
    while (i--) {
        id += urlAlphabet[(Math.random() * 64) | 0];
    }
    return id;
}

const sanitize = (string:string) => {
    const map:{[key: string]: string} = {
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#x27;",
        "/": "&#x2F;",
    };
    const reg = /[&<>"'/]/g;
    return string.replace(reg, (match:string ) => map[match]);
  };

export default function Header(){
    const {tasks,setTasks} = useContext(TaskContext);
    const addItem = useCallback((title:string) => {
        const task = {
            title: title, // Altere conforme necessário
            isDone: false
          };

          fetch(API_URL + "/tasks", {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(task)
          })
          .then(response => response.json()) 
          .then(data => {
            setTasks([data].concat(tasks))
          }) 
          .catch(error => console.error('Erro ao buscar dados:', error));

        
      },[tasks,setTasks]);
    const toggleAll = useCallback((e)=>{
        setTasks(tasks.map((task:Task)=>(task.isDone !== e.target.checked ? { ...task, isDone: e.target.checked } : task)))
    },[tasks,setTasks])
    const handleKeyDown = useCallback(
        (e) => {
            if (e.key === "Enter") {
                const value = e.target.value.trim();
                if (value.length >= 2){
                    addItem(sanitize(value));
                    e.target.value = "";
                }
            }
        },
        [addItem]
    );

    return (
        <header>
            <h1>todos</h1>
            <div css={inputbox}>
                <input data-testid="input-task" placeholder="O que precisa ser feito?" css={inputapp} onKeyDown={handleKeyDown}/>
                <div css={tooglebox}>
                    <label></label>
                </div>
            </div>
        </header>
    )
}