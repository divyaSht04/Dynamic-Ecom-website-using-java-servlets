package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.mysql.cj.Query;

import model.Cart;
import model.Order;
import model.PasswordEncryptionDecryption;
import model.Product;
import model.User;
import util.Stringutil;

public class Database {

	public Connection getConnection() throws ClassNotFoundException, SQLException {

		Class.forName(Stringutil.CONNECTION);
		String url = Stringutil.URL;
		String user = Stringutil.USER;
		String password = Stringutil.PASS;
		return DriverManager.getConnection(url, user, password);
	}

	public int addUser(User users) throws Exception {

		try (Connection conn = getConnection()) {
			PreparedStatement checkUsernameSt = conn.prepareStatement(Stringutil.GET_USERNAME);
			checkUsernameSt.setString(1, users.getUserName());
			ResultSet checkUsernameRs = checkUsernameSt.executeQuery();

			checkUsernameRs.next();
			if (checkUsernameRs.getInt(1) > 0) {
				return -2;
			}

			PreparedStatement checkPhoneSt = conn.prepareStatement(Stringutil.GET_PHONE);
			checkPhoneSt.setString(1, users.getPhoneNumber());
			ResultSet checkPhoneRs = checkPhoneSt.executeQuery();
			checkPhoneRs.next();

			if (checkPhoneRs.getInt(1) > 0) {
				return -4;
			}

			PreparedStatement checkEmailSt = conn.prepareStatement(Stringutil.GET_EMAIL);
			checkEmailSt.setString(1, users.getEmail());
			ResultSet checkEmailRs = checkPhoneSt.executeQuery();
			checkEmailRs.next();

			if (checkEmailRs.getInt(1) > 0) {
				return -3;
			}

			PreparedStatement st = conn.prepareStatement(Stringutil.INSERT_USER);

			st.setString(1, users.getFirstName());
			st.setString(2, users.getLastName());
			st.setString(3, users.getUserName());
			st.setString(4, users.getAddress());
			st.setString(5, users.getEmail());
			st.setString(6, users.getPhoneNumber());
			st.setString(7, PasswordEncryptionDecryption.encrypt(users.getPassword(), users.getUserName()));
			st.setString(8, users.getGender());
			st.setDate(9, Date.valueOf(users.getDateOfBirth()));
			st.setInt(10, 1);
			st.setString(11, users.getImageUrlFromPart());

			int result = st.executeUpdate();

			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("the problem: " + ex);
			return -1;
		}
	}

	public int getUser(String userName, String password) throws Exception {
		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement(Stringutil.GET_PASSWORD);
			ps.setString(1, userName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("Username found");
				String encryptedPasswordDB = rs.getString("password");
				String decryptedPasswordDB = PasswordEncryptionDecryption.decrypt(encryptedPasswordDB, userName);

				System.out.println("Entered password: " + password);
				System.out.println("Decrypted password from DB: " + decryptedPasswordDB);

				if (password.equals(decryptedPasswordDB)) {
					System.out.println("The value returned 1");
					return 1;
				} else {
					System.out.println("Password does not match");
					return 0;
				}
			} else {
				System.out.println("Username not found");
				return 0;
			}

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("The problem is: " + e.getMessage());
			return -1;
		}
	}

	public int getRole(String userName, String password) throws Exception {
		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement(Stringutil.GET_ROLE_PASSWORD);

			ps.setString(1, userName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String encryptedPasswordDB = rs.getString("password");
				String decryptedPasswordDB = PasswordEncryptionDecryption.decrypt(encryptedPasswordDB, userName);

				if (password.equals(decryptedPasswordDB)) {
					int role = rs.getInt("role");
					if (role == 1) {
						return 1;
					} else {
						return 2;
					}
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("The problem is: " + e.getMessage());
			return -1;
		}
	}

	ArrayList<Product> products = new ArrayList<Product>();

	public ArrayList<Product> getAllProducts(String option) {

		String query = "SELECT * FROM product";

		switch (option) {
		case "price":
			query += " ORDER BY price";
			break;
		case "rating":
			query += " ORDER BY rating";
			break;
		case "stock":
			query += " ORDER BY stock";
			break;
		default:
			break;
		}

		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Product items = new Product();
				items.setId(rs.getInt(Stringutil.PRODUCT_ID));
				items.setName(rs.getString(Stringutil.PRODUCT_NAME));
				items.setDescription(rs.getString(Stringutil.PRODUCT_DESCRIPTION));
				items.setImageUrlFromDB(rs.getString(Stringutil.PRODUCT_IMAGE));
				items.setPrice(rs.getFloat(Stringutil.PRODUCT_PRICE));
				items.setStock(rs.getInt(Stringutil.PRODUCT_STOCK));
				items.setRating(rs.getInt(Stringutil.PRODUCT_RATINGS));

				products.add(items);
			}

			for (Product items : products) {
				System.out.println(items);
			}

			return products;
		} catch (Exception e) {
			System.out.println("the problem is :" + e.getMessage());
			return null;
		}

	}

	ArrayList<User> user = new ArrayList<User>();

	public ArrayList<User> getUser(String username) {

		try (Connection conn = getConnection()) {
			PreparedStatement ps = conn.prepareStatement(Stringutil.GET_USER_FROM_USERNAME);
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User usermodel = new User();
				usermodel.setFirstName(rs.getString(Stringutil.FIRST_NAME));
				usermodel.setLastName(rs.getString(Stringutil.LAST_NAME));
				usermodel.setAddress(rs.getString(Stringutil.ADDRESS));
				usermodel.setPhoneNumber(rs.getString(Stringutil.PHONENUMBER));
				usermodel.setDateOfBirth(rs.getDate(Stringutil.DATE).toLocalDate());
				usermodel.setImageUrlFromDB(rs.getString(Stringutil.IMAGE));
				usermodel.setEmail(rs.getString(Stringutil.EMAIL));
				usermodel.setGender(rs.getString(Stringutil.GENDER));
				user.add(usermodel);
			}

			return user;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public ArrayList<Cart> getCartItems(ArrayList<Cart> items) {
		ArrayList<Cart> productsInfo = new ArrayList<Cart>();

		try (Connection conn = getConnection()) {
			for (Cart item : items) {
				PreparedStatement ps = conn.prepareStatement(Stringutil.GET_PRODUCT_FROM_PRODUCT_ID);
				ps.setInt(1, item.getId());

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					Cart cart = new Cart();
					cart.setId(rs.getInt(Stringutil.PRODUCT_ID));
					cart.setName(rs.getString(Stringutil.PRODUCT_NAME));
					cart.setPrice(rs.getFloat(Stringutil.PRODUCT_PRICE));
					cart.setTotalPrice(rs.getFloat(Stringutil.PRODUCT_PRICE) * item.getQuantity());
					cart.setQuantity(item.getQuantity());
					cart.setImageUrlFromDB(rs.getString(Stringutil.PRODUCT_IMAGE));
					cart.setStock(rs.getInt(Stringutil.PRODUCT_STOCK));
					productsInfo.add(cart);
				}
			}
			return (productsInfo);

		} catch (Exception e) {
			return null;
		}

	}

	
	public ArrayList<Order> getOrderInfo(ArrayList<Cart> items, float total, String userName) {
        ArrayList<Order> orderedItems = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement(Stringutil.GET_USER_FROM_USERNAME);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                for (Cart item : items) {
                    Order order = new Order();
                    User user = new User();
                    user.setUserName(userName);
                    order.setUser(user);
                    order.setQuantity(item.getQuantity());
                    order.setDate(LocalDate.now());
                    order.setTotalAmount(total);
                    orderedItems.add(order);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderedItems;
    }

    
    public int insertOrder(Order order, String userName) {
        int orderId = 0;
        try (Connection conn = getConnection()) {
        	int user_id = 0;
        	PreparedStatement ps1 = conn.prepareStatement(Stringutil.GET_USER_ID_FROM_USER);
        	order.getUser().setUserName(userName);
        	ps1.setString(1, order.getUser().getUserName());
        	ResultSet rs1 = ps1.executeQuery();
        	if(rs1.next()) {
        		user_id = rs1.getInt("id");
        	}
        	System.out.println(user_id);
        	
        	
            String sql = Stringutil.INSERT_ORDER;
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user_id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orderId;
    }
    
    public void insertOrderItems(int orderId, ArrayList<Cart> items, float total) {
        try (Connection conn = getConnection()) {
        	
            String sql = Stringutil.ORDER_PRODUCT;
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Cart item : items) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getId());
                ps.setFloat(3, total);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void updateProductStock(ArrayList<Cart> items) {
        try (Connection conn = getConnection()) {
            String sql = Stringutil.UPDATE_STOCK;
            PreparedStatement ps = conn.prepareStatement(sql);
            for (Cart item : items) {
                ps.setInt(1, item.getQuantity());
                ps.setInt(2, item.getId());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	public  void updateProducts(ArrayList<Product> products, int id) {
		try(Connection connection = getConnection()){
			 PreparedStatement ps = connection.prepareStatement("Delete from product where product_id = ?");
			 ps.setInt(1, id);
			 
			 int result = ps.executeUpdate();
			 if(result > 0) {
				 System.out.println("product Deleted");
			 }
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public int UpdateUSer(String userName, User userModel) {
		try(Connection conn = getConnection()){
			System.out.println(userModel.getFirstName());
			System.out.println(userModel.getImageUrlFromPart());
			PreparedStatement ps = conn.prepareStatement("UPDATE user_info SET firstName = ?, lastName = ?, dob = ?, address = ?, image = ? WHERE userName = ?");
			 ps.setString(1, userModel.getFirstName());
	         ps.setString(2, userModel.getLastName());
	         ps.setDate(3, Date.valueOf(userModel.getDateOfBirth()));
	         ps.setString(4, userModel.getAddress());
	         ps.setString(5, userModel.getImageUrlFromPart());
	         ps.setString(6, userName);
	         
	         int result = ps.executeUpdate();
	         if(result > 0) {
	        	 return 1;
	         }else {
				return 0;
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
		
	}

	public int addProduct(Product productModel) {
		
		try (Connection conn = getConnection()) {
			String Query = "Insert into product (name, price, stock, description, image, rating) values (?,?,?,?,?,?) ";

			PreparedStatement st = conn.prepareStatement(Query);

			st.setString(1, productModel.getName());
			st.setFloat(2, productModel.getPrice());
			st.setInt(3, productModel.getStock());
			st.setString(4, productModel.getDescription());
			st.setString(5, productModel.getImageUrlFromPart());
			st.setInt(6, productModel.getRating());
			
			int result = st.executeUpdate();

			return result > 0 ? 1 : 0;

		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("the problem: " + ex);
			return -1;
		}
	}


	public Product getProductById(int productId) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM product WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getFloat("price"));
                product.setStock(rs.getInt("stock"));
                return product;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateProduct(int productId, String name, float price, int stock, int ratings) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE product SET name = ?, price = ?, stock = ? , rating = ? WHERE product_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setFloat(2, price);
            ps.setInt(3, stock);
            ps.setInt(4, ratings);
            ps.setInt(5, productId);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
