package com.kishore.commands;

//implements CommandExecutor,Listener
public class AFK {
//    private final int cool = 180;
//    private Plugin plugin;
//    public HashMap<String,Boolean> checkreg = new HashMap<>();
//    private HashMap<String,Long> cooldown = new HashMap<>();
//    private HashMap<String,Boolean> afkcheck = new HashMap<>();
//    public AFK(Plugin plugin,Register register) {
//        this.plugin = plugin;
//        register.checkreg = checkreg;
//        plugin.getCommand("afk").setExecutor(this);
//        Bukkit.getPluginManager().registerEvents(this, plugin);
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        if(!(sender instanceof Player)) {
//        	plugin.getLogger().info(Util.Chat("&l&cThis command Can only Be Run By a Player"));
//            return true;
//        }
//        Player p =(Player) sender;
////        else {
//            if(p.isInvulnerable()) {
//            	if(afkcheck.containsKey(p.getName())) {
//                	TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cYOU ARE NOT AFK"),Util.Chat("&cANYMORE"));
//                	afkcheck.remove(p.getName());
//                    p.setInvulnerable(false);
//                    p.setPlayerListName(p.getName());
//            	}
//            	else{
//            		p.sendMessage(Util.Chat(" | &b&lAFK &r| &c&lYou Cannot Run This Command Right Now"));
//            		return true;
//            	}
//
//            }
//            else {
//                if(cooldown.get(p.getName()) == null) {
//                	TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU ARE AFK"));
//                	afkcheck.put(p.getName(), true);
//                    p.setPlayerListName(Util.Chat("[&c&lAFK&f] " + p.getName()));
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    cooldown.put(p.getName(),System.currentTimeMillis());
//                    p.setInvulnerable(true);
//                }
//                else {
//                    long remain = (System.currentTimeMillis() - cooldown.get(p.getName()))/1000;
//                    if(remain < cool) {
//                        int left = (int) (180-remain);
//                        p.sendMessage(Util.Chat(" | &b&lAFK &r| &c&lPlease wait " + left + " Seconds to Use This Command Again"));
//                    }
//                    else {
//                    	TitleAPI.sendTitle(p,20,60,20,Util.Chat("&aYOU ARE AFK"));
//                    	afkcheck.put(p.getName(), true);
//                        p.setPlayerListName(Util.Chat("[&c&lAFK&f] " + p.getName()));
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        cooldown.put(p.getName(),System.currentTimeMillis());
//                        p.setInvulnerable(true);
//                    }
//                }
//
//            }
////        }
//
//        return false;
//    }
//    @EventHandler
//    public void onPlayerMove(PlayerMoveEvent e) throws InterruptedException {
//        Player p = e.getPlayer();
//        if(p.isInvulnerable()) {
//        	boolean check = checkreg.get(p.getName());
//        	if(check == false) {
//        		
//        	}
//        	else {
//        		if(afkcheck.containsKey(p.getName())) {
//        			if(e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) {
//                		
//                	}
//            		else {
//            			afkcheck.remove(p.getName());
//            			TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cYOUR WILL LOSE YOUR"),Util.Chat("&cINVULNERABILITY IN"));
//        				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//        					public void run() {
//        						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cIN"),Util.Chat("&b5"));
//        					}
//        				},20L);
//        				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//        					public void run() {
//        						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cIN"),Util.Chat("&b4"));
//        					}
//        				},40L);
//        				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//        					public void run() {
//        						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cIN"),Util.Chat("&a3"));
//        					}
//        				},60L);
//        				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//        					public void run() {
//        						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cIN"),Util.Chat("&a2"));
//        					}
//        				},80L);
//        				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//        					public void run() {
//        						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cIN"),Util.Chat("&c1"));
//        					}
//        				},100L);
//        				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
//        					
//        					@Override
//        					public void run() {
//        						TitleAPI.sendTitle(p,20,60,20,Util.Chat("&cYOU ARE NOT AFK"),Util.Chat("&cANYMORE"));	
//    	            			p.setInvulnerable(false);
//    	    		            p.setPlayerListName(p.getName());
//        					}
//        				},120L);
//            								
//    				}
//        		}
//        		else {
//        			
//        		}
//
//        		}
//        	}
//        }
    }
    


