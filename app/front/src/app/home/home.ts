import { Component, input, InputSignal, OnInit } from '@angular/core';
import { Api, UsuarioResponse, UsuarioRequest, cedulaDuplicadaResponse } from '../servicios/api';

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
  mostrarFormulario: boolean = false;

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

  agregarNuevoUsuario(
    cedula: string, 
    nombre: string, 
    correo: string, 
    fechaNacimiento: string
  ): void {

    const numeroCedula = Number(cedula);

    if (isNaN(numeroCedula)) {
      alert('La cédula debe ser un número válido.');
      return;
    }
  
    const nuevoUsuario: UsuarioRequest = {
      cedula: numeroCedula,
      nombre,
      correo,
      fechaNacimiento
    };

    const camposInvalidos = Object.entries(nuevoUsuario)
      .filter(([_, valor]) => valor == null || valor === '')
      .map(([campo]) => campo);

    if (camposInvalidos.length > 0) {
      alert(`Por favor completa los siguientes campos: ${camposInvalidos.join(', ')}`);
      return;
    }
    this.api.agregarUsuario(nuevoUsuario).subscribe({
      next: (respuesta) => {       
        if (respuesta.error) {
          alert(respuesta.error);
          return;
        }

        this.datos = [...this.datos, respuesta];
        this.mostrarFormulario = false;
        console.log('Usuario agregado correctamente:', respuesta);
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
