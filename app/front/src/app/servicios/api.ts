import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface Usuario {
  id: number;
  nombre: string;
  correo: string;
  edad: number;
}

@Injectable({
  providedIn: 'root'
})
export class Api {

  private apiUrl = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}
    obtenerDatos(): Observable<Usuario[]> {
      return this.http.get<Usuario[]>(this.apiUrl);
  }
}
