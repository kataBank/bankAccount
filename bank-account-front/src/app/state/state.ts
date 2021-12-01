
export enum ActionsTypes{
    GET_ALL_CLIENTS="[Client] Get All clients",
    GET_SELECTED_CLIENT="[Client] Get Selected client",
    NEW_CLIENT="[Client] New client",
    ADD_CLIENT="[Client] New client",
    INFO_ACCOUNT ="[Account] New Account"
  }

  export interface ActionEvent {
    type:ActionsTypes,
    payload?:any
  }
  export enum DataStateEnum {
    LOADING,
    LOADED,
    ERROR
  }

  export interface AppDataState<T> {
    dataState:DataStateEnum,
    data?:T,
    errorMessage?:string
  }
