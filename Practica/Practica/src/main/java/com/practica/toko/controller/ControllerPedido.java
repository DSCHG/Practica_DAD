package com.practica.toko.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practica.toko.model.*;
import com.practica.toko.repositorios.PedidoRepository;


@Controller
public class ControllerPedido {
	
	private Usuario user;
	
	@Autowired
	private PedidoRepository pedidos;
	
	@RequestMapping("/verMisPedidos")
	public String mostrarPedidos(Model model,HttpSession session, HttpServletRequest request) {
		model.addAttribute("user", request.isUserInRole("USER"));
		user = (Usuario) session.getAttribute("usuario");
		double preciototal = 0.0;
		model.addAttribute("haydatos", user.getListaPedidos().size());
		model.addAttribute("pedidos", user.getListaPedidos());
		for(Pedido p : user.getListaPedidos()) {
			model.addAttribute("id", p.getId());
			for(Producto pro : p.getProducto()) {
				preciototal += pro.getPrecio();
			}
			p.setPrecio(preciototal);
			model.addAttribute("precio", p.getPrecio());
			preciototal=0;
		}
		session.setAttribute("usuario", user);
		return "mostrarPedidos";
	}
	@RequestMapping("/InfoPedidos")
	public  String Mostrar(Model model,@RequestParam (value= "id") int id,HttpSession session, HttpServletRequest request) {
		model.addAttribute("user", request.isUserInRole("USER"));
		Pedido pedido=pedidos.findById(id).get();
		float preciototal=0;
		model.addAttribute("haydatos", pedido.getId_producto().size()-1);
		model.addAttribute("productos", pedido.getProducto());
		for(Producto pro : pedido.getProducto()) {
			model.addAttribute("id", pro.getId());
			model.addAttribute("nombre", pro.getNombre());
			model.addAttribute("precio",pro.getPrecio());
			preciototal += pro.getPrecio();
		}
		//model.addAttribute("precioTotal", preciototal);
		
		return "InfoPedido";
	}
	/*@RequestMapping("/InfoPedido")
	public String mostrarPedido(Model model,@RequestParam (value= "id") int id,HttpSession session, HttpServletRequest request) {
		model.addAttribute("user", request.isUserInRole("USER"));
		user = (Usuario) session.getAttribute("usuario");
		double preciototal=0;
		System.out.println("HOILAAAAAAA");
		Pedido pedido=pedidos.findById(id).get();
		for(Pedido p:user.getListaPedidos()) {
			if(p.getId()==id) {
				pedido=p;
			}
		}
		model.addAttribute("haydatos", pedido.getId_producto().size()-1);
		model.addAttribute("productos", pedido.getProducto());
		for(Producto pro : pedido.getProducto()) {
			model.addAttribute("id", pro.getId());
			model.addAttribute("nombre", pro.getNombre());
			model.addAttribute("precio",pro.getPrecio());
			preciototal += pro.getPrecio();
		}
		//model.addAttribute("precioTotal", preciototal);
	
		return "InfoPedido";
	}*/

}
