import { Component, input, InputSignal, OnInit } from '@angular/core';
import { Api, UsuarioResponse, UsuarioRequest } from '../servicios/api';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit{
  datos: UsuarioResponse[] = [];
  inputCedula: InputSignal<bigint | undefined> = input<bigint>();
  inputNombre: InputSignal<string | undefined> = input<string>();
  inputCorreo: InputSignal<string | undefined> = input<string>();
  inputFecha: InputSignal<string | undefined> = input<string>();

  constructor(private api: Api) {}
  
  ngOnInit(): void {
    this.api.obtenerDatos().subscribe({
      next: (respuesta) => {
        this.datos = respuesta;
      },
      error: (error) => {
        console.error('Error al obtener los datos:', error);
      }
    });
  }
  mostrarFormulario: boolean = false;

  agregarNuevoUsuario(
    cedula: string, 
    nombre: string, 
    correo: string, 
    fechaNacimiento: string): void {
      
    const nuevoUsuario: UsuarioRequest = {
      cedula: Number(cedula),
      nombre: nombre,
      correo: correo,
      fechaNacimiento: fechaNacimiento
    };
    this.api.agregarUsuario(nuevoUsuario).subscribe({
      next: (respuesta) => {
        console.log('Usuario agregado:', respuesta);
        alert(respuesta.toString());
        this.datos = [...this.datos, respuesta];
      },
      error: (error) => {
        console.error('Error al agregar usuario:', error);
      }
      });
  }

  actualizarUsuario(cedula: bigint): void {
      const nuevoUsuario: UsuarioRequest = {
      cedula: 101010,
      nombre: 'quemado',
      correo: 'quemado@mail.com',
      fechaNacimiento: '2020-02-02'
      };
  this.api.actualizarUsuario(cedula, nuevoUsuario).subscribe({
    next: (respuesta) => {
      this.datos = this.datos.map(usuario => usuario.cedula === cedula ? respuesta : usuario);
    } ,
    error: (error) => {
      console.error('Error al actualizar usuario:', error);
    }
  })
  }


}
