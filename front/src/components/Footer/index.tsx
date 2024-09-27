/** @jsxImportSource @emotion/react */
import { useCallback, useContext, useMemo } from "react";
import {countTasksFooter, filtersFooter,listFootter} from "./styles";
import { TaskContext } from "../../TaskContext";
import { useLocation } from "react-router-dom";
import classnames from "classnames";

export default function Footer (){
  const { pathname: route } = useLocation();
  const {tasks,setTasks} = useContext(TaskContext);
  const activeTasks = useMemo(() => tasks.filter((task) => !task.isDone), [tasks]);
  return (
    <footer data-testid="footerlist" css={listFootter}>
      <span css={countTasksFooter}>{`${activeTasks.length} ${activeTasks.length>1?"itens ativos!":"item ativo!"}`} </span>
      <ul css={filtersFooter}>
        <li> <a data-testid="filterAll" className={classnames({ selected: route === "/" })} href="#/">Todos</a> </li>
        <li> <a data-testid="filterTodo" className={classnames({ selected: route === "/ativos" })} href="#/ativos">Ativos</a></li>
        <li> <a data-testid="filterDone" className={classnames({ selected: route === "/realizados" })} href="#/realizados">Realizados</a></li>
      </ul>
    </footer>
  )
}