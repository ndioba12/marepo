import {ApiPaginator} from './meta-data.model';

export class ResponseApi {
  status: string;
  payload?: any;
  message?: string;
  errors?: string;
  metadata?: ApiPaginator;
}
