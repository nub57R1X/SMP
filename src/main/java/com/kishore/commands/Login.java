package com.kishore.commands;

public class Login {
//	private Plugin plugin;
//	private Register register;
//	private ResultSet result = null;
//	private HashMap<String,Boolean> checklog = new HashMap<>();
//	private Connection conn;
//	private Statement state; 
//	private boolean move = false;
//	private boolean checkcmd = false;
//	private Location l;
//	
//	public Login(Plugin plugin,Register register) {
//		this.plugin = plugin;
//		this.register = register;
//		plugin.getCommand("login").setExecutor(this);//		this.register.checklog = checklog;
//		try {
//			String ip = "";
//			String username = "";
//			String pass = "";
//			conn = DriverManager.getConnection(ip,username,pass);
//			plugin.getLogger().info(Util.Chat("&l&aConnected to The Login Servers"));
//			state = conn.createStatement();
//		} catch (SQLException e) {
//			plugin.getLogger().info(Util.Chat(Util.Chat("&l&cCouldnt Connect to the Login Servers")));
//				e.printStackTrace();
//		}
//	}
//		
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		String checkpass = null;
//		if(!(sender instanceof Player)) {
//			plugin.getLogger().info(Util.Chat("&l&cThis command Can only Be Run By a Player"));
//			return true;
//		}
//		Player p = (Player) sender;
//		l = register.getL(); 
//		boolean check = checklog.get(p.getName());
//		if(check == false) {
//			if(args.length == 0) {
//				p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lPlease Enter Your Password too"));				
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//					
//					@Override
//					public void run() {
//						p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lType /mclogin <password> to Continue"));
//					}
//				},20L);
//				
//		
//				return true;
//			}
//			
//			String pass1 = args[0];
//			try {
//				result = state.executeQuery("select pass from register where ign = '" + p.getName() + "'");
//				if(!(result.next())){
//					p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lYou have to Register First"));
//				}
//				checkpass = result.getString("pass");
//				
////				System.out.println(checkpass  + "        " + pass1);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			if(pass1.equals(checkpass)) {
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//					
//					@Override
//					public void run() {
//						p.setInvulnerable(false);
//						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aSUCCESSFULLY"),Util.Chat("&bLOGGED IN"));
//					}
//				},20L);
//				register.getCheckdone().put(p.getName(), true);
//				register.getMine().removePassenger(p);
//				register.getMine().remove();
//				p.teleport(l);
//				try {
//					int result = state.executeUpdate("update register set ip = '" + p.getAddress().getHostString() + "' where ign = '" + p.getName() + "'");
//				} catch (SQLException e1) {
//					e1.printStackTrace();
//				}
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//					
//					@Override
//					public void run() {
//						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aWELCOME BACK"),Util.Chat("&b" + p.getName()));
//					}
//				},60L);
//				
//				checklog.put(p.getName(),true);
//				
//				//heartgiver after resurrecting
//				try {
//					result = state.executeQuery("select * from outcheck where ign = '" + p.getName() + "'");
//					if(result.next()) {
//						String check2 = result.getString("outsmp");
//						if(check2.equals("false")) {
//							TitleAPI.sendTitle(p, 20, 60, 20, Util.Chat("&aYOU HAVE BEEN RESURRECTED"));
//							plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//								
//								@Override
//								public void run() {
//									generate(p);
//								}
//							},100L);
//			
//							int result = state.executeUpdate("delete from outcheck where ign = '" + p.getName() + "'");
//						}
//					}
//				}
//				catch(SQLException error) {
//					
//				}
//				
//			}
//			else {
//				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//					
//					@Override
//					public void run() {
//						p.kickPlayer(Util.Chat(" | &b&lLogin &r| &c&lThe Password is Incorrect Pls Try Again"));						
//					}
//				},20L);
//				return true;
//			}
//		}
//		else {
//			p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lYou Are Already Logged In"));
//		}
//			
//		return false;
//	}
//	
//	public void generate(Player p) {
//		Random rand = new Random();
//		int randnum = rand.nextInt(15 - 5 + 1) + 5;
//		TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aGENERATING RANDOM HEARTS"),Util.Chat("&bPLEASE WAIT"));
//		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//			public void run() {
//				TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU WILL PLAY WITH"),Util.Chat("&c" + randnum + " HEARTS"));
//			}
//		},100L);
//		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//			public void run() {
//				p.setMaxHealth(randnum*2);
//				p.setHealth(randnum*2);
//			}
//		},220L);
//		
//	}
	
	
}
