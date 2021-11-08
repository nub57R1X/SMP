package com.kishore.commands;

public class Register{
//	private static Plugin plugin;
//	private String pass2;
//	private String pass1;
//	private Connection conn;
//	private Statement state;
//	private ResultSet result = null;
//	private boolean move = true;
//	public HashMap<String,Boolean> checkreg = new HashMap<>();
//	public HashMap<String,Boolean> checklog = new HashMap<>();
//	public HashMap<String,Boolean> checkdone = new HashMap<>();
//	private Player p;
//	private Location l;
//	private Location lol = new Location(Bukkit.getWorld("world"),0,200,0);
//	private Location lo2;
//	private Entity mine;
//	public Register(Plugin plugin) {
//		this.plugin = plugin;
//		plugin.getCommand("register").setExecutor(this);
//		Bukkit.getPluginManager().registerEvents(this,plugin);
//		try {

//			conn = DriverManager.getConnection(ip,username,pass);
//			plugin.getLogger().info(Util.Chat("&l&aConnected To The Register Servers"));
//			state = conn.createStatement();
//		} catch (SQLException e) {
//			plugin.getLogger().info(Util.Chat("&l&cCouldnt Connect to the Register Servers"));
//			e.printStackTrace();
//		}
//		
//	}
//
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		if(cmd.getName().equalsIgnoreCase("register")) {
//			if(!(sender instanceof Player)) {
//				plugin.getLogger().info(Util.Chat("&l&cThis command Can only Be Run By a Player"));
//				return true;
//			} 
//			Player p = (Player) sender;
//			boolean check = checkreg.get(p.getName());
//			if(check == false) {
//				if(args.length == 0) {
//					p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lThis Command Needs 2 Entries with it"));
//					p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&l/register <password> <confirm password>"));
//					return true;
//				}
//				String pass1 = args[0];
//				String pass2 = args[1];
////				System.out.println(pass1 + "    " + pass2);
//				if(pass1.equals(pass2)) {
//					int done = 0;
//					try {
//						done = state.executeUpdate("insert into register values('" + p.getName() + "', '" + p.getAddress().getHostString() + "', '" + pass1 + "')");
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//					if(done == 0) {
//						p.sendMessage(Util.Chat("| &b&lRegister &r| &c&lCouldnt Register You Try Again"));
//						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//							public void run() {
//								p.sendMessage(Util.Chat(" | &b&lRegister &r| &cIf This Error still Persists Please Contact &b&l57R1X#0001 &con Discord"));
//							}
//						},20L);
//				
//						return true;
//					}
//					else {
//						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//							
//							@Override
//							public void run() {
//								checkdone.put(p.getName(), true);
//							}
//						},20L);
//					
//						
//						try {
//							result = state.executeQuery("select * from hearts where ign = '" + p.getName() + "'");
//							if(result.next()) {
//								
//							}
//							else {
//								//generate
//								generate(p);
////								System.out.println("hearts boi");
//								int result = state.executeUpdate("insert into hearts values('" + p.getName() + "')");
////								System.out.println("hearts boi 2");
////								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
////									
////									@Override
////									public void run() {
////										p.sendMessage(Util.Chat("| &b&lInvulnerable &r| &a&lYour Invulnerability Wore Off"));
////										p.setInvulnerable(false);
////									}
////								},36000L);
////								plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
////									
////									@Override
////									public void run() {
////										p.setInvulnerable(true);
////										p.sendMessage(Util.Chat("| &b&lInvulnerable &r| &a&lYou will be Invulnerable for The Next 30 Minutes"));								
////									}
////								},100L);
//							}
//						}
//						catch(SQLException error) {
//							
//						}
//						
//						checkreg.put(p.getName(), true);
//						mine.removePassenger(p);
//						mine.remove();
//						p.teleport(l);
//						move = true;
//						p.setInvulnerable(false);
//						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aWELCOME TO"),Util.Chat("&b57R1X SMP"));
//						p.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//							
//							@Override
//							public void run() {
//								System.out.println("Welcome delay Ignore");
//							}
//						},20L);
//					}
//				}
//				else {
//					p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lPasswords Dont Match Try Again"));
//					return true;
//				}
//			}
//			else {
//				p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lYou Cannot Run This command as You Already Registered"));
//			}
//		}
//		try {
//			result.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}	
//		return false;
//	}
//	
//	public void generate(Player p) {
//		Random rand = new Random();
//		int randnum = rand.nextInt(15 - 7 + 1) + 7;
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
//	
//	@EventHandler
//	public void OnJoin(PlayerJoinEvent e) throws SQLException, InterruptedException {
//		p = e.getPlayer();
////		System.out.println("boi boi boi");
//		try {
//			result = state.executeQuery("select pass from register where ign = '" + p.getName() + "'");
//			if(!(result.next())) {
////				System.out.println("Success");
//				l = p.getLocation();
//				Location lol = new Location(Bukkit.getWorld("world"),0,200,0);
//				p.teleport(lol);
//				checkreg.put(p.getName(), false);
//				checkdone.put(p.getName(), false);
//				Location current = p.getLocation();
//				mine = p.getWorld().spawnEntity(current, EntityType.MINECART);
//				mine.setPassenger(p);
//				TitleAPI.sendTitle(p,20,100,20,Util.Chat("&aTO REGISTER TYPE"),"/register <pass> <confirm_pass>");
////				p.sendMessage(Util.Chat("&e------------------------------------------------------------"));
////				p.sendMessage();
////				p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lPlease Register to Continue"));
////				p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lType /mcregister to Continue"));
////				p.sendMessage();
////				p.sendMessage(Util.Chat("&e------------------------------------------------------------"));
////				System.out.println("check done false");
//			}
//			else {
//				checkreg.put(p.getName(), true);
//				String ip = p.getAddress().getHostString();
//				Statement getipst = conn.createStatement();
//				ResultSet getip = getipst.executeQuery("select ip from register where ign = '" + p.getName() + "'");
//				getip.next();
//				String checkip = getip.getString("ip");
//				if(ip.equals(checkip)) {
//					checklog.put(p.getName(), true);
//					TitleAPI.sendTitle(p,20,60,20,Util.Chat("&bAUTOMATIC VERIFICATION"),Util.Chat("&aSUCCESSFUL"));
//					plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//						
//						@Override
//						public void run() {
//							checkdone.put(p.getName(), true);
//							try {
//								getipst.close();
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//							try {
//								getip.close();
//							} catch (SQLException e) {
//								e.printStackTrace();
//							}
//							
//							
//							//heartgiver after ressurection
//							try {
//								heartgiver(p);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//						}
//					},60L);
//				}
//				else {
//					checklog.put(p.getName(), false);
//				}
//				
//				boolean check = checklog.get(p.getName());
//				if(check == false) {
//					l = p.getLocation();
//					Location lol = new Location(Bukkit.getWorld("world"),0,200,0);
//					p.teleport(lol);
//					Location current = p.getLocation();
//					mine = p.getWorld().spawnEntity(current, EntityType.MINECART);
//					mine.setPassenger(p);
//					checkdone.put(p.getName(), false);
//					TitleAPI.sendTitle(p,20,100,20,Util.Chat("&aTO LOGIN TYPE"),"/login <pass>");
//				}
//			}
//		}
//		catch(Exception error) {
//			System.out.println("Error : " + error.getMessage());
//		}
//	}
//	
//	
//	public Entity getMine() {
//		return mine;
//	}
//
//	public void setMine(Entity mine) {
//		this.mine = mine;
//	}
//
//	@EventHandler
//    public void disablecommandreload(PlayerCommandPreprocessEvent e) throws InterruptedException{
//		Player p = e.getPlayer();
//		String cmd = e.getMessage();
//		String split[] = cmd.split(" ");
//		String check = split[0];
//		boolean checkbool = checkdone.get(p.getName());
//		if(checkbool == false) {
//			if(checkreg.get(p.getName()) == false){
//				if(check.contains("register")) {
//					
//				}
//				else{
//					p.sendMessage(Util.Chat(" | &b&lRegister &r| &c&lYou Cannot Run This Command Right Now"));
//					e.setCancelled(true);
//				}
//			}
//			else if(checklog.get(p.getName()) == false) {
//				if(check.contains("login")) {
//					
//				}
//				else{
//					p.sendMessage(Util.Chat(" | &b&lLogin &r| &c&lYou Cannot Run This Command Right Now"));
//					e.setCancelled(true);
//				}
//			}
//		}
//		else {
//			
//		}
//    }
//	
//	@EventHandler
//	public void onQuit(PlayerQuitEvent e) {
//		if(checkreg.containsKey(p.getName()) || checklog.containsKey(p.getName())) {
//			if(checkreg.get(p.getName()) == false || checklog.get(p.getName()) == false) {
//				p.teleport(l);
//				mine.removePassenger(p);
//				mine.remove();
//			}
//		}
//	}
//	
//	
//	public void heartgiver(Player p) throws InterruptedException {
//		try {
//			result = state.executeQuery("select outsmp from outcheck where ign = '" + p.getName() + "'");
//			if(result.next()) {
//				p.setInvulnerable(false);
//				String check = result.getString("outsmp");
//				if(check.equals("false")) {
//					
//						TitleAPI.sendTitle(p, 20, 60, 20, Util.Chat("&aYOU HAVE BEEN RESURRECTED"),"");
//						Random rand = new Random();
//						int randnum = rand.nextInt(15 - 7 + 1) + 7;
//						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//							
//							@Override
//							public void run() {
//								TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aGENERATING RANDOM HEARTS"),Util.Chat("&bPLEASE WAIT"));
//								
//							}
//						},80L);
//						plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//							
//							@Override
//							public void run() {
//								TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU WILL PLAY WITH"),Util.Chat("&c" + randnum + " HEARTS"));
//								p.setMaxHealth(randnum*2);
//								p.setHealth(randnum*2); 
//								
//								try {
//									int result = state.executeUpdate("delete from outcheck where ign = '" + p.getName() + "'");
//								} catch (SQLException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
//							}
//						},140L);
//
//					
//				}
//			}
//		}
//		catch(SQLException error) {
//			
//		}
//	}
//	
//
//
//	public Location getL() {
//		return l;
//	}
//
//	public void setL(Location l) {
//		this.l = l;
//	}
//
//	public HashMap<String, Boolean> getCheckdone() {
//		return checkdone;
//	}
//
//	public void setCheckdone(HashMap<String, Boolean> checkdone) {
//		this.checkdone = checkdone;
//	}
	
	
	
	
	
}
