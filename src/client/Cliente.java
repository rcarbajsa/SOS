package client;
import java.io.*;
import java.net.URI;
import java.sql.SQLException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import views.*;
import resources.*;
import org.apache.tomcat.jni.Buffer;
import org.glassfish.jersey.client.ClientConfig;

import com.mysql.fabric.Response;

public class Cliente {
	private ClientConfig config;
	private Client client; 
	private WebTarget target;
	public Cliente() {
		this.config = new ClientConfig();
		this.client = ClientBuilder.newClient(config);
		this.target=this.client.target(getBaseURI());
	}
	public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
				new Cliente().init();
			
	}
	private void init() throws NumberFormatException, IOException, SQLException {
		int option=0;
		do {
			System.out.println("Bienvenido");
			System.out.println("Elija una de las siguientes opciones:");
			System.out.println("1: Crear un usuario");
			System.out.println("2: Eliminar un usuario");
			System.out.println("3: Editar un usuario");
			System.out.println("4: Obtener los usuarios de la base de datos");
			System.out.println("5: Crear un post");
			System.out.println("6: Eliminar un post");
			System.out.println("7: Editar un post");
			System.out.println("8: Obtener los posts de un usuario");
			System.out.println("9: Obtener los posts de los amigos de un usuario");
			System.out.println("10: Enviar un mensaje");
			System.out.println("11: Añadir un amigo");
			System.out.println("12: Eliminar un amigo");
			System.out.println("13: Obtener los amigos de un usuario");
			System.out.println("14: Obtener información personal de un usuario");
			System.out.println("15: Terminar");
			option=readInt();
			switch(option) {
				case 1:
					crearUsuario();
					break;
				case 2:
					eliminarUsuario();
					break;
				case 3:
					editarUsuario();
					break;
				case 4:
					getUsuarios();
					break;
				case 5:
					crearPost();
					break;
				case 6:
					eliminarPost();
					break;
				case 7:
					editarPost();
					break;
				case 8:
					getPosts();
					break;
				case 9:
					getFriendsPosts();
					break;
				case 10:
					enviarChat();
					break;
				case 11:
					añadirAmigo();
					break;
				case 12:
					eliminarAmigo();
					break;
				case 13:
					getFriends();
					break;
				case 14:
					getUser();
					break;
				case 15:
					option=15;
					break;
				
			}
		}
		while(option !=15);
		System.exit(0);
	
	}
	private void getUser() {
		// TODO Auto-generated method stub
		System.out.println(this.target.path("user").path("1").request().accept(MediaType.APPLICATION_JSON).get(String.class));
	}
	private void getFriends() {
		// TODO Auto-generated method stub
		
	}
	private void eliminarAmigo() {
		// TODO Auto-generated method stub
		
	}
	private void añadirAmigo() {
		// TODO Auto-generated method stub
		
	}
	private void enviarChat() {
		// TODO Auto-generated method stub
		
	}
	private void getFriendsPosts() {
		// TODO Auto-generated method stub
		
	}
	private void getPosts() {
		// TODO Auto-generated method stub
		System.out.println(this.target.path("user").path("1").request().accept(MediaType.APPLICATION_JSON).get(String.class));
	}
	private void editarPost() {
		// TODO Auto-generated method stub
		
	}
	private void eliminarPost() {
		// TODO Auto-generated method stub
		
	}
	private void crearPost() {
		// TODO Auto-generated method stub
		
	}
	private void getUsuarios() {
		// TODO Auto-generated method stub
		
	}
	private void editarUsuario() {
		// TODO Auto-generated method stub
		
	}
	private void eliminarUsuario() {
		// TODO Auto-generated method stub
		
	}
	private void crearUsuario() throws SQLException, IOException {
		UserResource user= new UserResource();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Introduzca username:");
		user.setUsername(buffer.readLine());
		System.out.println("Introduzca name:");
		user.setName(buffer.readLine());
		Response res = this.target.path("user").request().accept(MediaType.APPLICATION_JSON).post(Entity.json(user), Response.class);
		System.out.println(res);
		
		
	}
	private static int readInt() throws NumberFormatException, IOException {
		System.out.println(">");
		return Integer.parseInt(new BufferedReader(
				new InputStreamReader(System.in)).readLine());
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/SOS/api/").build();
	}

}
