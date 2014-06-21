package com.mahadahack.geolocalizacion.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Repository;

import com.mahadahack.geolocalizacion.model.Filtro;
import com.mahadahack.geolocalizacion.model.Posicion;
import com.mahadahack.geolocalizacion.model.Servicio;
import com.mahadahack.geolocalizacion.mongo.Location;
import com.mahadahack.geolocalizacion.mongo.LocationRepository;

// @formatter:off

@Repository
public class ServiciosRepository {

	private SimpleDriverDataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	private LocationRepository locationRepository;
	
	@Autowired
	public ServiciosRepository(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
		
		// connect database
		dataSource = new SimpleDriverDataSource();
	    dataSource.setDriverClass(org.h2.Driver.class);
	    dataSource.setUsername("admin");
	    dataSource.setUrl("jdbc:h2:mem");
	    dataSource.setPassword("");
	    
	    jdbcTemplate = new JdbcTemplate(dataSource);

	    createDatabaseTables(jdbcTemplate);

	    loadExampleData();
	}
	
	public List<Servicio> getServicesByFitlro(Filtro filtro){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp fecha = null;
		try {
			fecha = new Timestamp(sdf.parse(filtro.getFecha()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Object []  params = {
			filtro.getCategoria(),
			fecha
		};
		return jdbcTemplate.query("SELECT * FROM servicio WHERE categoria = ? and ? BETWEEN fecha_inicio AND fecha_fin",  params, getServicio());
	
	}
	
	public void insertService(Servicio servicio) {
		jdbcTemplate.update(
				"INSERT INTO servicio(categoria, fecha_inicio, fecha_fin, descripcion, id_servicio, id_usuario, email, mongo_pos) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?)", 
				servicio.getCategoria(),
				servicio.getFecha_inicio(),
				servicio.getFecha_fin(),
				servicio.getDescripcion(),
				servicio.getId_servicio(),
				servicio.getId_usuario(),
				servicio.getEmail(),
				servicio.getMongo_pos());
	}
	
	private void createDatabaseTables(JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("drop table servicio if exists");
        jdbcTemplate.execute("create table servicio(" +
        		"id serial, " +
        		"categoria varchar(255), " +
        		"fecha_inicio timestamp, " +
        		"fecha_fin timestamp, " +
        		"descripcion varchar(255), " +
        		"id_servicio varchar(255)," +
        		"id_usuario varchar(255)," +
        		"email varchar(255)," +
        		"mongo_pos varchar(255))");
	}
	
	private void loadExampleData() {
		Calendar f_inicio = Calendar.getInstance();
		f_inicio.add(Calendar.DAY_OF_MONTH, -10);
		Calendar f_fin = Calendar.getInstance();
		f_fin.add(Calendar.DAY_OF_MONTH, 10);

		Servicio servicio = new Servicio("p_ing", new Timestamp(
				f_inicio.getTimeInMillis()), new Timestamp(
				f_fin.getTimeInMillis()), "Profesor de ingles", "p_ing_1",
				"muzzy", "email@gmail.com", "pos_ing_1");
		insertService(servicio);
		servicio = new Servicio("p_ing", new Timestamp(
				f_inicio.getTimeInMillis()), new Timestamp(
				f_fin.getTimeInMillis()), "Profesor de ingles", "p_ing_2",
				"muzzy", "email@gmail.com", "pos_ing_2");
		insertService(servicio);
		servicio = new Servicio("p_ing", new Timestamp(
				f_inicio.getTimeInMillis()), new Timestamp(
				f_fin.getTimeInMillis()), "Profesor de ingles", "p_ing_3",
				"muzzy", "email@gmail.com", "pos_ing_3");
		insertService(servicio);

		servicio = new Servicio("zoo",
				new Timestamp(f_inicio.getTimeInMillis()), new Timestamp(
						f_fin.getTimeInMillis()), "Zoo de madrid", "zoo_1",
				"zoo", "email@gmail.com", "pos_zoo_1");
		insertService(servicio);
		servicio = new Servicio("zoo",
				new Timestamp(f_inicio.getTimeInMillis()), new Timestamp(
						f_fin.getTimeInMillis()), "Zoo de madrid", "zoo_1",
				"zoo", "email@gmail.com", "pos_zoo_2");
		insertService(servicio);
		servicio = new Servicio("zoo",
				new Timestamp(f_inicio.getTimeInMillis()), new Timestamp(
						f_fin.getTimeInMillis()), "Zoo de madrid", "zoo_1",
				"zoo", "email@gmail.com", "pos_zoo_3");
		insertService(servicio);

		servicio = new Servicio("algo", new Timestamp(
				f_inicio.getTimeInMillis()), new Timestamp(
				f_fin.getTimeInMillis()), "Algo", "algo_1", "algo",
				"email@gmail.com", "pos_algo_1");
		insertService(servicio);
		servicio = new Servicio("algo", new Timestamp(
				f_inicio.getTimeInMillis()), new Timestamp(
				f_fin.getTimeInMillis()), "Algo", "algo_1", "algo",
				"email@gmail.com", "pos_algo_2");
		insertService(servicio);
		servicio = new Servicio("algo", new Timestamp(
				f_inicio.getTimeInMillis()), new Timestamp(
				f_fin.getTimeInMillis()), "Algo", "algo_1", "algo",
				"email@gmail.com", "pos_algo_3");
		insertService(servicio);
	}
	
	public RowMapper<Filtro> getFiltroMapper() {
		
		return new RowMapper<Filtro>() {
            @Override
            public Filtro mapRow(ResultSet rs, int rowNum) throws SQLException {
                Filtro aux = new Filtro(
                	rs.getString("categoria"),
                	rs.getString("fecha"),
                	rs.getString("hora"),
                	rs.getString("nick"));
                return aux;
            }
        };
		
	}
	
	public RowMapper<Servicio> getServicio() {
		
		return new RowMapper<Servicio>() {
            @Override
            public Servicio mapRow(ResultSet rs, int rowNum) throws SQLException {
            	Servicio aux = new Servicio(
            		rs.getString("categoria"),
            		rs.getTimestamp("fecha_inicio"),
            		rs.getTimestamp("fecha_fin"),
            		rs.getString("descripcion"),
            		rs.getString("id_servicio"),
            		rs.getString("id_usuario"),
            		rs.getString("email"),
            		rs.getString("mongo_pos"));
            	
        		Location loc = locationRepository.findOne(aux.getMongo_pos());
        		Posicion position = new Posicion(loc.getPosition()[0],loc.getPosition()[1]);
            	aux.setPosition(position);
            	
                return aux;
            }
        };
		
	}

}
